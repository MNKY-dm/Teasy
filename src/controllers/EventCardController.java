package controllers;

// Gère les événements à afficher dynamiquement sur les différentes pages HomeScene et EventScene

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import models.Event;

public class EventCardController {

    // Import des éléments de EventCard.fxml
    @FXML
    private AnchorPane cardRoot;
    @FXML
    private ImageView eventImage;
    @FXML
    private Label eventTitle;

    private int eventId;

    public void setEventData(Event event){
        this.eventId = event.getId();
        eventTitle.setText(event.getName());

        // Récupérer les images liées à l'événement

    }
}
