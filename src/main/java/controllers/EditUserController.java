package controllers;

import dao.UserDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import models.User;
import services.SessionManager;

import java.util.regex.Pattern;

public class EditUserController {
    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,}$", Pattern.CASE_INSENSITIVE);

    private static final Pattern PHONE_PATTERN =
            Pattern.compile("^[0-9+() .-]{6,20}$");

    @FXML private Label userTitle;
    @FXML private Label infoLabel;
    @FXML private TextField userId;
    @FXML private TextField userNom;
    @FXML private TextField userEmail;
    @FXML private TextField userTel;
    @FXML private ChoiceBox<String> userRole;
    @FXML private TextField userCreatedAt;
    @FXML private Button deleteButton;

    private User user;

    public void setUser(User user) {
        this.user = user;

        userTitle.setText(user.getNom());
        userId.setText(String.valueOf(user.getId()));
        userNom.setText(user.getNom());
        userEmail.setText(user.getEmail());
        userTel.setText(user.getTel() == null ? "" : user.getTel());
        userRole.setValue(user.getRole());
        userCreatedAt.setText(user.getCreated_at() == null ? "" : user.getCreated_at().toString());

        boolean selfUser = isCurrentSessionUser(user);
        userRole.setDisable(selfUser);
        deleteButton.setDisable(selfUser);

        if (selfUser) {
            infoLabel.setText("Ton propre rôle et ta suppression sont bloqués depuis ce panel.");
        } else {
            infoLabel.setText("");
        }
    }

    public void saveUser() {
        if (user == null) {
            return;
        }

        String nom = normalize(userNom.getText());
        String email = normalize(userEmail.getText()).toLowerCase();
        String tel = normalize(userTel.getText());
        String role = isCurrentSessionUser(user) ? user.getRole() : userRole.getValue();

        String error = validateUser(nom, email, tel, role);
        if (error != null) {
            infoLabel.setText(error);
            return;
        }

        User updatedUser = new User(
                nom,
                email,
                user.getPassword(),
                tel.isBlank() ? null : tel,
                role
        );
        updatedUser.setId(user.getId());
        updatedUser.setCreated_at(user.getCreated_at());

        boolean success = UserDAO.updateRowById(updatedUser);
        if (success) {
            User refreshedUser = UserDAO.getRowById(updatedUser.getId());
            this.user = refreshedUser == null ? updatedUser : refreshedUser;
            setUser(this.user);
            infoLabel.setText("Utilisateur mis à jour.");
        } else {
            infoLabel.setText("La mise à jour a échoué.");
        }
    }

    public void deleteUser() {
        if (user == null) {
            return;
        }

        if (isCurrentSessionUser(user)) {
            infoLabel.setText("Tu ne peux pas supprimer ton propre compte depuis ce panel.");
            return;
        }

        boolean success = UserDAO.deleteRowById(user.getId());
        if (success) {
            toUsersManagement();
        } else {
            infoLabel.setText("Suppression impossible.");
        }
    }

    public void toUsersManagement() {
        AppController.getInstance().loadUsersManagement();
    }

    private String validateUser(String nom, String email, String tel, String role) {
        if (nom.isBlank()) {
            return "Le nom est obligatoire";
        }
        if (nom.length() > 100) {
            return "Le nom est trop long";
        }

        if (email.isBlank()) {
            return "L'email est obligatoire";
        }
        if (email.length() > 255) {
            return "L'email est trop long";
        }
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            return "Format d'email invalide";
        }

        User existingUser = UserDAO.getRowByEmail(email);
        if (existingUser != null && existingUser.getId() != null && !existingUser.getId().equals(user.getId())) {
            return "Email déjà utilisé";
        }

        if (!tel.isBlank()) {
            if (tel.length() > 20) {
                return "Le numéro de téléphone est trop long";
            }
            if (!PHONE_PATTERN.matcher(tel).matches()) {
                return "Format de téléphone invalide";
            }
        }

        if (role == null || role.isBlank()) {
            return "Le rôle est obligatoire";
        }

        return null;
    }

    private boolean isCurrentSessionUser(User candidate) {
        User currentSessionUser = SessionManager.getInstance().getCurrentUser();
        return currentSessionUser != null
                && currentSessionUser.getId() != null
                && candidate.getId() != null
                && currentSessionUser.getId().equals(candidate.getId());
    }

    private String normalize(String value) {
        return value == null ? "" : value.trim();
    }
}