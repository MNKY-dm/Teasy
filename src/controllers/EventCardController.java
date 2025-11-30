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
            List<Photo> pictures = EventDAO.getPictures(eventId);

            // Tenter de récupérer les images liées à l'événement
            // Penser à renommer les différentes entités pour mieux s'y retrouver : "Photo" ; "Picture" ; "Image"
            if (!pictures.isEmpty()) { // Si on trouve une/des image(s) liée(s) à cet événement
                String picUrl = pictures.getFirst().getUrl(); // On récupère l'URL de la première

                if (picUrl != null && !picUrl.trim().isEmpty()) { // Si l'URL n'est pas NULL ou n'est pas vide ("")
                    try {
                        Image image = new Image(picUrl); // On modélise l'image à partir de son URL
                        eventPic.setImage(image); // On l'affiche dans le conteneur FXML eventPic
                    } catch (Exception ex) {
                        System.err.println("Erreur chargement image : " + picUrl);
                        // Mettre une image par défaut (à mettre plus tard)
                        // eventPic.setImage(new Image("url locale"));
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("EventCardController : erreur dans setEventData ; Impossible de récupérer les infos de l'événement " + e.getMessage());
        }
    }

    private void showEvent(MouseEvent event){
        System.out.println("Card cliquée : ID évenement --> " + eventId);
    }
}
