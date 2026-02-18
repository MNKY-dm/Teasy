package controllers;

// Gère les événements à afficher dynamiquement sur les différentes pages HomeScene et EventScene

import dao.EventDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import models.Event;
import models.Photo;

import java.sql.SQLException;
import java.util.List;

public class EventCardController {

    // Import des éléments de EventCard.fxml
    @FXML
    private AnchorPane cardRoot;
    @FXML
    private ImageView eventPic;
    @FXML
    private Label eventTitle;

    private int eventId;

    public void setEventData(Event event){

        try {
            // Tenter de récupérer les infos de l'événement voulu
            this.eventId = event.getId();
            eventTitle.setText(event.getName());
            String affiche = EventDAO.getRowById(eventId).getAffiche();
            System.out.println("setEventData pour l'event " + eventId);
            System.out.println("setEventData pour l'event " + eventId + " affiche " + affiche);
//            System.out.println("setEventData pour l'event " + eventId + " pictures " + pictures.size());

            // Tenter de récupérer les images liées à l'événement
            // Penser à renommer les différentes entités pour mieux s'y retrouver : "Photo" ; "Picture" ; "Image"

            if (affiche != null && event.isAfficheUrlValid()) { // Si l'URL n'est pas NULL et n'est pas vide (""), et qu'elle ne renvoie pas de code erreur HTTP
                try {
                    Image image = new Image(affiche); // On modélise l'image à partir de son URL
                    System.out.println("Url valide : " + affiche);
                    System.out.println("Event associé : " + eventId);
                    eventPic.setImage(image); // On l'affiche dans le conteneur FXML eventPic
                } catch (Exception ex) {
                    System.err.println("Erreur chargement image : " + affiche);
                    // Mettre une image par défaut
                     eventPic.setImage(new Image("/pics/default_event_pic.png"));
                }
            } else {
                // Mettre une image par défaut
                eventPic.setImage(new Image("/pics/default_event_pic.png"));
            }
        } catch (Exception e) {
            System.err.println("EventCardController : erreur dans setEventData ; Impossible de récupérer les infos de l'événement " + e.getMessage());
        }
    }

    @FXML
    private void showEvent(MouseEvent event){
        System.out.println("Card cliquée : ID événement --> " + eventId);
        AppController.getInstance().loadEvent(dao.EventDAO.getRowById(eventId));
    }
}
