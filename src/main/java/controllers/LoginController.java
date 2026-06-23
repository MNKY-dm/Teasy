package controllers;

import dao.UserDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import models.User;
import services.SessionManager;
import utils.PasswordUtils;

// Gérait initialement le changement de scene dans moveToRegister()

public class LoginController {

    // Composants FXML
    @FXML
    private TextField tfmail;

    @FXML
    private PasswordField tfpassword;

    @FXML
    private Label lbinfo;



    // btnConnect() : Bouton "Se connecter"
    @FXML
    public void btnConnect() {
        // Récupérer les données du formulaire
        String email = tfmail.getText();
        String password = tfpassword.getText();

        // vérifier si les champs sont bien remplis
        if (email.isEmpty() || password.isEmpty()) {
            lbinfo.setText("Veuillez remplir tous les champs");
            return;
        }

        // Chercher l'utilisateur en base de données
        User user = UserDAO.getRowByEmail(email);

        // Vérifier si l'utilisateur existe et si le mot de passe est correct
        if (user != null && PasswordUtils.checkPassword(password, user.getPassword())) {

            // CONNEXION RÉUSSIE

            // Sauvegarder l'utilisateur dans la session
            SessionManager.getInstance().login(user);

            // Charger la page HomeScene.fxml via AppController
            AppController.getInstance().loadHome();

            lbinfo.setText("Connexion réussie !");

        } else {

            // CONNEXION ÉCHOUÉE
            lbinfo.setText("Email ou mot de passe incorrect");
            tfpassword.clear();  // Vider le champ password pour recommencer facilement
        }
    }


    // moveToRegister() : Bouton "S'inscrire"
    @FXML
    public void moveToRegister() {
        // Utiliser AppController pour la navigation
        AppController.getInstance().loadRegister();
    }
}
