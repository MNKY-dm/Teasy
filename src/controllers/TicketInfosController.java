package controllers;

import dao.TicketDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import models.Ticket;

public class TicketInfosController {
    @FXML
    private Label ticketID;

    @FXML
    private Label eventName;

    @FXML
    private Label userName;

    @FXML
    private Label ticketType;

    @FXML
    private Label seanceID;

    @FXML
    private Label price;

    @FXML
    private Label ticketStatus;

    public void setTicketsInfos(int ticketID) {
        System.out.println("setTicketsInfos pour le ticket " + ticketID);

        try {
            Ticket ticket = TicketDAO.getRowById(ticketID);

            this.ticketID.setText(ticketID + "");
            eventName.setText(ticket.getTitle());
            userName.setText(ticket.getUser());
            ticketType.setText(ticket.getType());
            seanceID.setText(ticket.getSeance_id() + "");
            price.setText(ticket.getPrice() + "");
            ticketStatus.setText(ticket.getStatus());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
