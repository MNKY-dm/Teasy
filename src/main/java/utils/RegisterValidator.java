package utils;

import dao.UserDAO;
import services.PwnedPasswordService;

import java.util.regex.Pattern;

public final class RegisterValidator {

    private static final int NOM_MAX = 100;
    private static final int EMAIL_MAX = 255;
    private static final int TEL_MAX = 20;
    private static final int PASSWORD_MIN = 12;

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,}$", Pattern.CASE_INSENSITIVE);

    private static final Pattern PHONE_PATTERN =
            Pattern.compile("^[0-9+() .-]{6,20}$");

    private static final Pattern LOWERCASE_PATTERN =
            Pattern.compile(".*[a-z].*");

    private static final Pattern UPPERCASE_PATTERN =
            Pattern.compile(".*[A-Z].*");

    private static final Pattern DIGIT_PATTERN =
            Pattern.compile(".*\\d.*");

    private static final Pattern SPECIAL_PATTERN =
            Pattern.compile(".*[^A-Za-z0-9].*");

    private RegisterValidator() {
    }

    public static String validate(String nom,
                                  String email,
                                  String tel,
                                  String password,
                                  String passwordConfirm) {

        nom = normalize(nom);
        email = normalize(email).toLowerCase();
        tel = normalize(tel);

        if (nom.isBlank()) {
            return "Le nom est obligatoire";
        }
        if (nom.length() > NOM_MAX) {
            return "Le nom est trop long";
        }

        if (email.isBlank()) {
            return "L'email est obligatoire";
        }
        if (email.length() > EMAIL_MAX) {
            return "L'email est trop long";
        }
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            return "Format d'email invalide";
        }
        if (UserDAO.getRowByEmail(email) != null) {
            return "Email déjà utilisé";
        }

        if (!tel.isBlank()) {
            if (tel.length() > TEL_MAX) {
                return "Le numéro de téléphone est trop long";
            }
            if (!PHONE_PATTERN.matcher(tel).matches()) {
                return "Format de téléphone invalide";
            }
        }

        if (password == null || password.isBlank()) {
            return "Le mot de passe est obligatoire";
        }
        if (passwordConfirm == null || passwordConfirm.isBlank()) {
            return "La confirmation du mot de passe est obligatoire";
        }
        if (!password.equals(passwordConfirm)) {
            return "Les mots de passe ne correspondent pas";
        }
        if (password.length() < PASSWORD_MIN) {
            return "Le mot de passe doit contenir au moins 12 caractères";
        }
        if (!LOWERCASE_PATTERN.matcher(password).matches()) {
            return "Le mot de passe doit contenir au moins une minuscule";
        }
        if (!UPPERCASE_PATTERN.matcher(password).matches()) {
            return "Le mot de passe doit contenir au moins une majuscule";
        }
        if (!DIGIT_PATTERN.matcher(password).matches()) {
            return "Le mot de passe doit contenir au moins un chiffre";
        }
        if (!SPECIAL_PATTERN.matcher(password).matches()) {
            return "Le mot de passe doit contenir au moins un caractère spécial";
        }
        if (!PwnedPasswordService.isSafe(password)) {
            return "Ce mot de passe a déjà fuité, choisis-en un autre";
        }


        return null;
    }

    private static String normalize(String value) {
        return value == null ? "" : value.trim();
    }
}
