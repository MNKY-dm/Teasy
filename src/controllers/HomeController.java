package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import models.User;
import services.SessionManager;

import java.net.URL;
import java.util.ResourceBundle;


// HomeController : Gère la page d'accueil de l'application

public class HomeController implements Initializable {

    @FXML
    private Label lbUserEmail;

    @FXML
    private Label lbUserRole;


    // initialize() : Appelé dès que la scene FXML est chargée
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Récupérer l'utilisateur connecté de la session
        User currentUser = SessionManager.getInstance().getCurrentUser();

        // Vérifier qu'on est bien connecté (par sécurité)
        if (currentUser == null) {
            System.out.println("[ERREUR] HomeController : Aucun utilisateur connecté !");
            AppController.getInstance().loadLogin();
            return; // interrompt la fonction si pas d'utilisateur connecté
        }

          // Afficher les infos de l'utilisateur
//        lbUserEmail.setText("Email : " + currentUser.getEmail());
//        lbUserRole.setText("Rôle : " + currentUser.getRole());

        System.out.println("[HOMECONTROLLER] Page d'accueil chargée pour : "
                + currentUser.getEmail());
    }


    // btnGoToEvents() : Bouton "Voir les événements" ; permet à l'utilisateur de consulter la page présentant les événements en fonction de sa recherche, de différentes sections...
    @FXML
    public void btnGoToEvents() {
        System.out.println("[HOMECONTROLLER] Navigation vers Events");
        // AppController.getInstance().loadEvents();  // Décommenter une fois fonctionnalité terminée
        System.out.println("Cette page n'existe pas encore !");
    }


    // btnGoToProfile() : Bouton "Mon profil" ; permet à l'utilisateur de consulter son profil (servira surtout lorsque plus de fonctionnalités seront codées
    @FXML
    public void btnGoToProfile() {
        System.out.println("[HOMECONTROLLER] Navigation vers Profile");
        // AppController.getInstance().loadProfile();  // Décommenter une fois fonctionnalité terminée
        System.out.println("Cette page n'existe pas encore !");
    }


    // btnGoToTickets() : Bouton "Mes billets" ; permet à l'utilisateur de consulter son historique
    @FXML
    public void btnGoToTickets() {
        System.out.println("[HOMECONTROLLER] Navigation vers Tickets");
//        AppController.getInstance().loadTickets();  // Décommenter une fois fonctionnalité terminée
        System.out.println("Cette page n'existe pas encore !");
    }


    // btnLogout() : Bouton "Se déconnecter" dans le menu de l'application
    @FXML
    public void btnLogout() {
        System.out.println("[HOMECONTROLLER] Déconnexion...");

        // AppController gère la déconnexion et redirection
        AppController.getInstance().loadLogout();
    }
}
