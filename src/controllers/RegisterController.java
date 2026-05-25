package controllers;

import dao.UserDAO;
import javafx.application.Platform;
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

        // Vérifier que les mots de passe correspondent
        if (!password.equals(passwordConfirm)) {
            lbinfo.setText("Les mots de passe ne correspondent pas");
            return;
        }

        // Vérifier que l'email n'existe pas déjà en BDD
        if (UserDAO.getRowByEmail(email) != null) {
            lbinfo.setText("Email déjà utilisé");
            return; // Interrompt la méthode si jamais le mail est déjà inscrit en BDD
        }

        // Hasher le mot de passe avant de l'enregistrer
        String hashedPassword = PasswordUtils.hashPassword(password);

        // Créer nouvel utilisateur
        User newUser = new User(nom, email, hashedPassword, tel, role);

        // l'ajouter en base de données
        UserDAO.insertNewRow(newUser);

        // INSCRIPTION RÉUSSIE
        lbinfo.setText("Compte créé avec succès ! Redirection...");

        // Rediriger vers la page de connexion après un délai de 1.5 sec
        new Thread(() -> {
            try {
                Thread.sleep(1500);
                Platform.runLater(() -> {
                    AppController.getInstance().loadLogin();
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

//  Bouton "Se connecter"
    @FXML
    public void moveToConnect() {
        AppController.getInstance().loadLogin();
    }
}
