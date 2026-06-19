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

    public void setTicketsInfos(int ticketID) {
        System.out.println("setTicketsInfos pour le ticket " + ticketID);

        try {
            Ticket ticket = TicketDAO.getRowById(ticketID);

            Seance seance =  SeanceDAO.getRowById(ticket.getSeance_id());

            this.ticketID.setText(ticketID + "");
            eventName.setText(ticket.getTitle());
            userName.setText(ticket.getUser());
            ticketType.setText(ticket.getType());
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
