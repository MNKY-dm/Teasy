package controllers;

import dao.UserDAO;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import models.User;
import services.PwnedPasswordService;
import utils.PasswordUtils;
import utils.RegisterValidator;

// Gérait initialement le changement de scene dans moveToConnect()

public class RegisterController {

    @FXML
    private TextField tfnom;

    @FXML
    private TextField tfmail;

    @FXML
    private TextField tftel;

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
        String nom = tfnom.getText().trim();
        String email = tfmail.getText().trim().toLowerCase();
        String tel = tftel.getText().trim();
        String password = tfpassword.getText();
        String passwordConfirm = tfpasswordConfirm.getText();

        String error = RegisterValidator.validate(nom, email, tel, password, passwordConfirm);
        if (error != null) {
            lbinfo.setText(error);
            return;
        }

        if (UserDAO.getRowByEmail(email) != null) {
            lbinfo.setText("Email déjà utilisé");
            return;
        }

        boolean uncompromised = PwnedPasswordService.isSafe(password);
        if (!uncompromised) {
            lbinfo.setText("Mot de passe compromis, choisis-en un autre");
            return;
        }

        String hashedPassword = PasswordUtils.hashPassword(password);
        User newUser = new User(nom, email, hashedPassword, tel.isBlank() ? null : tel, "client");
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
