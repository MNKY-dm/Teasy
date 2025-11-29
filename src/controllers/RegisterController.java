package controllers;

import dao.UserDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import models.User;
import utils.PasswordUtils;

// Gérait initialement le changement de scene dans moveToConnect()

public class RegisterController {

    @FXML
    private TextField tfnom;

    @FXML
    private TextField tfmail;

    @FXML
    private TextField tftel;

    @FXML
    private TextField tfrole;

    @FXML
    private PasswordField tfpassword;

    @FXML
    private PasswordField tfpasswordConfirm;

    @FXML
    private Label lbinfo;

    @FXML
    private Label lbconfirm;

//    @FXML  À ajouter plus tard lors de la fonctionnalité des suggestions en fonction de l'emplacement
//    private TextField tffrom;


//  btnRegister() : Bouton "S'inscrire"

    @FXML
    public void btnRegister() {
        // Récupérer les données du formulaire fxml
        String nom = tfnom.getText();
        String email = tfmail.getText();
        String password = tfpassword.getText();
        String passwordConfirm = tfpasswordConfirm.getText();
        String tel = tftel.getText();
        String role = tfrole.getText();
//        String ville = tffrom.getText();

        // Vérifier si tous les champs sont remplis
        if (email.isEmpty() || password.isEmpty() || passwordConfirm.isEmpty()
                || role.isEmpty()) {
            lbinfo.setText("Champs obligatoires manquants");
            return;
        }

        // 3. Vérifier que les passwords correspondent
        if (!password.equals(passwordConfirm)) {
            lbconfirm.setText("Les mots de passe ne correspondent pas");
            return;
        }

        // 4. Vérifier que l'email n'existe pas
        if (UserDAO.getRowByEmail(email) != null) {
            lbinfo.setText("Email déjà utilisé");
            return;
        }

        // Hasher le mot de passe avant de l'enregistrer
        String hashedPassword = PasswordUtils.hashPassword(password);

        // Créer nouvel utilisateur
        User newUser = new User(nom, email, hashedPassword, tel, role);

        // l'ajouter en base de données
        UserDAO.insertNewRow(newUser);

        // ✅ INSCRIPTION RÉUSSIE
        lbinfo.setText("Compte créé avec succès ! Redirection...");

        // Rediriger vers Login après un délai
        new Thread(() -> {
            try {
                Thread.sleep(1500);  // Attendre 1.5 secondes
                AppController.getInstance().loadLogin();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

//  Bouton "Se connecter"
    @FXML
    public void moveToConnect() {
        // Utiliser AppController pour la navigation
        AppController.getInstance().loadLogin();
    }
}
