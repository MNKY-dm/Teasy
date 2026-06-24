package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import models.Seance;
import models.Ticket;
import utils.TypeConverter;

public class TicketInfosController {
    @FXML private Label ticketID;
    @FXML private Label eventName;
    @FXML private Label userName;
    @FXML private Label ticketType;
    @FXML private Label seanceID;
    @FXML public Label seanceLocation;
    @FXML public Label seanceDate;
    @FXML private Label price;
    @FXML private Label ticketStatus;

    public void setTicketsInfos(Ticket ticket) {
        try {
            ticketID.setText(String.valueOf(ticket.getId()));
            eventName.setText(ticket.getTitle());
            userName.setText(ticket.getUser());
            ticketType.setText(ticket.getType());

            Seance seance = ticket.getSeance();

            if (seance != null) {
                seanceID.setText(String.valueOf(ticket.getSeance_id()));
                seanceLocation.setText(seance.getLocation());
                seanceDate.setText(TypeConverter.dateFormat(seance.getDate()));
            } else {
                seanceID.setText(String.valueOf(ticket.getSeance_id()));
                seanceLocation.setText("-");
                seanceDate.setText("-");
            }

            price.setText(String.valueOf(ticket.getPrice()));
            ticketStatus.setText(ticket.getStatus());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
