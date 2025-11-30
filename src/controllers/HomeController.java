package controllers;

import dao.EventDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import models.Event;
import models.User;
import services.SessionManager;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


// HomeController : Gère la page d'accueil de l'application

public class HomeController implements Initializable {

//    @FXML
//    private Label lbUserEmail;
//
//    @FXML
//    private Label lbUserRole;

    @FXML
    private HBox eventsToComeHbox;


    // initialize() : Appelé dès que la scene FXML est chargée
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Récupérer l'utilisateur connecté de la session
        User currentUser = SessionManager.getInstance().getCurrentUser();

        // Vérifier qu'on est bien connecté (par sécurité)
        if (currentUser == null) {
            System.out.println("Erreur dans HomeController : Aucun utilisateur connecté.");
            AppController.getInstance().loadLogin();
            return; // interrompt la fonction si pas d'utilisateur connecté
        }

          // Afficher les infos de l'utilisateur
//        lbUserEmail.setText("Email : " + currentUser.getEmail());
//        lbUserRole.setText("Rôle : " + currentUser.getRole());

        System.out.println("HomeController : Page d'accueil chargée pour : "
                + currentUser.getEmail());
        loadEventsToCome();
    }

    // Méthode qui va permettre de charger les événements à venir sur la page d'accueil, en les piochant dans la BDD
    private void loadEventsToCome() {
        // Récupérer les événements dans la base de données
        List<Event> events = EventDAO.getAll();

        // Afficher chaque événement dans une card
        for (Event event : events) {
            addEventCard(event);
            System.out.println("Événement affiché : " + event.getId() + " ; nom : " + event.getName());
        }
    }

    // Méthode qui permet d'afficher les différents éléments dans la partie "Événements à venir"
    private void addEventCard(Event event) {
        try {
            // Charger le FXML de la card
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/EventCard.fxml"));
            AnchorPane cardRoot = loader.load();

            // Charger le controller et set up les infos de la card selon l'event
            EventCardController eventCardController = loader.getController();
            eventCardController.setEventData(event);

            // L'ajouter au HBOX
            eventsToComeHbox.getChildren().add(cardRoot);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // btnGoToEvents() : Bouton "Voir les événements" ; permet à l'utilisateur de consulter la page présentant les événements en fonction de sa recherche, de différentes sections...
    @FXML
    public void btnGoToEvents() {
        System.out.println("HomeController : Navigation vers Events");
        // AppController.getInstance().loadEvents();  // Décommenter une fois fonctionnalité terminée
        System.out.println("Cette page n'existe pas encore !");
    }


    // btnGoToProfile() : Bouton "Mon profil" ; permet à l'utilisateur de consulter son profil (servira surtout lorsque plus de fonctionnalités seront codées
    @FXML
    public void btnGoToProfile() {
        System.out.println("HomeController : Navigation vers Profile");
        // AppController.getInstance().loadProfile();  // Décommenter une fois fonctionnalité terminée
        System.out.println("Cette page n'existe pas encore !");
    }


    // btnGoToTickets() : Bouton "Mes billets" ; permet à l'utilisateur de consulter son historique
    @FXML
    public void btnGoToTickets() {
        System.out.println("HomeController : Navigation vers Tickets");
//        AppController.getInstance().loadTickets();  // Décommenter une fois fonctionnalité terminée
        System.out.println("Cette page n'existe pas encore !");
    }


    // btnLogout() : Bouton "Se déconnecter" dans le menu de l'application
    @FXML
    public void btnLogout() {
        System.out.println("HomeController : Déconnexion");

        // AppController gère la déconnexion et redirection
        AppController.getInstance().loadLogout();
    }
}
