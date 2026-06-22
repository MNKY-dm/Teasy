package controllers;

import dao.SeanceDAO;
import dao.TicketDAO;
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
        System.out.println("setTicketsInfos pour le ticket " + ticketID);

        try {

            ticketID.setText(ticket.getId() + "");
            eventName.setText(ticket.getTitle());
            userName.setText(ticket.getUser());
            ticketType.setText(ticket.getType());

            Seance seance = ticket.getSeance();
            seanceID.setText(ticket.getSeance_id() + "");
            seanceLocation.setText(seance.getLocation());
            seanceDate.setText(TypeConverter.dateFormat(seance.getDate()));

            price.setText(ticket.getPrice() + "");
            ticketStatus.setText(ticket.getStatus());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
