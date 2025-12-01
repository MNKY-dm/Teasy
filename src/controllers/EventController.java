package controllers;


import dao.EventDAO;
import dao.SeanceDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import models.Event;
import models.Photo;
import models.Seance;
import models.User;
import services.SessionManager;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

// Class qui est utilisée lorsque l'utilisateur clique sur un événement
public class EventController implements Initializable {

    private int eventId;

    private Event event;

    @FXML
    private Label eventTitle;

    @FXML
    private ImageView eventAffiche;

    @FXML
    private Label eventDescription;

    @FXML
    private VBox seancesInfos;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { // paramètres non utilisés
        // Récupérer l'utilisateur connecté de la session
        User currentUser = SessionManager.getInstance().getCurrentUser();

        // Débugger
        System.out.println("initialize() appelée");
        System.out.println("eventTitle null ? " + (eventTitle == null));
        System.out.println("eventAffiche null ? " + (eventAffiche == null));
        System.out.println("eventDescription null ? " + (eventDescription == null));

        // Vérifier qu'on est bien connecté (par sécurité)
        if (currentUser == null) {
            System.out.println("Erreur dans EventController : Aucun utilisateur connecté.");
            AppController.getInstance().loadLogin();
            return; // interrompt la fonction si pas d'utilisateur connecté
        }

        System.out.println("EventController : Événement chargé pour : " + currentUser.getEmail());

        loadSeanceInfos();
    }

    public void loadSeanceInfos() {

        System.out.println("loadSeanceInfos est bien lancée. Event_id : " + eventId);
        // Récupérer les séances de l'événement courant
        List<Seance> seances = EventDAO.getSeances(eventId);

        // Afficher chaque événement dans une card
        for (Seance seance : seances) {
            addSeanceInfos(seance);
            System.out.println("Séance affichée : " + seance.getDate() + " ; pour l'événement : " + seance.getEvent_id());
        }
    }

    // Méthode qui permet d'afficher les différents éléments dans la partie "Événements à venir"
    private void addSeanceInfos(Seance seance) {
        try {
            // Charger le FXML de la card
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/SeanceInfos.fxml"));
            AnchorPane cardRoot = loader.load();

            // Charger le controller et set up les infos de la card selon l'event
            SeanceInfosController seanceInfosController = loader.getController();
            seanceInfosController.setSeanceInfos(seance);

            // L'ajouter au HBOX
            seancesInfos.getChildren().add(cardRoot);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setEvent(Event event) {
        setEventId(event);
        this.event = event;
        System.out.println("setEvent affiche l'event courant : " + event);
        setEventTitle(event);
        setEventDescription(event);
        setEventAffiche(event);
    }

    private void setEventId(Event event) {
        this.eventId = event.getId();
    }

    @FXML
    private void setEventTitle(Event event) {
//        System.out.println("SetEventTitle pour l'event suivant : " + event.toString());
        try {
            // Tenter de récupérer le titre de l'événement voulu
            String title = event.getName();
            eventTitle.setText(title);

        } catch (Exception e) {
            System.err.println("EventController : erreur dans setEventTitle ; Impossible de récupérer le titre de l'événement " + e.getMessage());
        }
    }

    @FXML
    private void setEventAffiche(Event event) {

        try {
            // Tenter de récupérer les infos de l'événement voulu
            this.eventId = event.getId();
            String affiche = event.getAffiche();

            // Tenter de récupérer l'affiche liée à l'événement pour l'afficher

            if (affiche != null && event.isAfficheUrlValid()) { // Si l'URL n'est pas NULL et n'est pas vide (""), et qu'elle ne renvoie pas de code erreur HTTP
                try {
                    Image image = new Image(affiche); // On modélise l'affiche à partir de son URL
                    System.out.println("Url valide : " + affiche);
                    eventAffiche.setImage(image); // On l'affiche dans le conteneur FXML eventAffiche
                } catch (Exception ex) {
                    System.err.println("Erreur chargement image : " + affiche);
                    // Mettre une image par défaut
                    eventAffiche.setImage(new Image("/pics/default_event_pic.png"));
                }
            } else {
                // Mettre une image par défaut
                eventAffiche.setImage(new Image("/pics/default_event_pic.png"));
            }

        } catch (Exception e) {
            System.err.println("EventController : erreur dans setEventData ; Impossible de récupérer l'affiche l'événement. " + e.getMessage());
        }
    }

    @FXML
    private void setEventDescription(Event event) {

        try {
            // Tenter de récupérer les infos de l'événement voulu
            this.eventId = event.getId();
            String description = event.getDescription();

            // Tenter de récupérer la description liée à l'événement pour l'afficher

            if (description != null) {
                try {
                    System.out.println("Description à afficher : " + description);
                    eventDescription.setText(description); // On l'affiche dans le conteneur FXML eventDescription
                } catch (Exception ex) {
                    System.err.println("Erreur chargement description : " + ex.getMessage());
                    // Mettre un texte par défaut
                    eventDescription.setText("Venez tous à cet événement génial ! (promis c'est pas de l'IA)");
                }
            } else {
                // Mettre un texte par défaut
                eventDescription.setText("Venez tous à cet événement génial ! (promis c'est pas de l'IA)");
            }

        } catch (Exception e) {
            System.err.println("EventController : erreur dans setEventDescription ; Impossible de récupérer les infos de l'événement." + e.getMessage());
        }
    }

    @FXML
    public void moveToHome(ActionEvent actionEvent) {
        System.out.println("EventController : moveToHome");
        AppController.getInstance().loadHome();

    }

    @FXML
    public void moveToBuyTicket(ActionEvent actionEvent) {
        System.out.println("EventController : moveToBuyTicket");
        AppController.getInstance().loadBuyTicket(dao.EventDAO.getRowById(eventId));
    }
}
