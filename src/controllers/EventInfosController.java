package controllers;

import dao.EventDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import models.Event;
import utils.TypeConverter;

public class EventInfosController {
    @FXML private Label eventId;
    @FXML private Label eventName;
    @FXML private Label eventCreatorId;

    public void setEventInfos(int eventId) {
        System.out.println("setEventsInfos pour l'événement : " + eventId);

        try {
            Event event = EventDAO.getRowById(eventId);

            this.eventId.setText(eventId + "");
            eventName.setText(event.getName());
            eventCreatorId.setText(event.getCreator_id() + "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
