package controllers;

import dao.TicketDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import models.Ticket;

import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class AdminPanelController implements Initializable {
    @FXML
    private Label nbStandardTickets;

    @FXML
    private Label nbPlusTickets;

    @FXML
    private Label nbVIPTickets;

    @FXML
    private Label nbTotalTickets;

    @FXML
    private Label nbValidTickets;

    @FXML
    private Label nbExpiredTickets;

    @FXML
    private Label nbRefundedTickets;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTicketsStats();
    }

    @FXML
    public void setTicketsStats() {
        List<Ticket> tickets = TicketDAO.getAll();
        nbStandardTickets.setText(countStandardTickets(tickets));
        nbPlusTickets.setText(countPlusTickets(tickets));
        nbVIPTickets.setText(countVIPTickets(tickets));
        nbTotalTickets.setText(String.valueOf(tickets.size()));
        nbValidTickets.setText(countValidTickets(tickets));
        nbExpiredTickets.setText(countExpiredTickets(tickets));
        nbRefundedTickets.setText(countRefundedTickets(tickets));

    }

    public String countStandardTickets(List<Ticket> tickets) {
        int nbStandardTickets = 0;
        for (Ticket ticket : tickets) {
            if (Objects.equals(ticket.getType(), "STANDARD")) {
                 nbStandardTickets = nbStandardTickets + 1 ;
            }
        }
        return String.valueOf(nbStandardTickets);
    }

    public String countPlusTickets(List<Ticket> tickets) {
        int nbPlusTickets = 0;
        for (Ticket ticket : tickets) {
            if (Objects.equals(ticket.getType(), "PLUS")) {
                 nbPlusTickets = nbPlusTickets + 1 ;
            }
        }
        return String.valueOf(nbPlusTickets);
    }

    public String countVIPTickets(List<Ticket> tickets) {
        int nbVIPTickets = 0;
        for (Ticket ticket : tickets) {
            if (Objects.equals(ticket.getType(), "VIP")) {
                 nbVIPTickets = nbVIPTickets + 1 ;
            }
        }
        return String.valueOf(nbVIPTickets);
    }

    public String countValidTickets(List<Ticket> tickets) {
        int nbValidTickets = 0;
        for (Ticket ticket : tickets) {
            if (Objects.equals(ticket.getStatus(), "available")) {
                nbValidTickets = nbValidTickets + 1 ;
            }
        }
        return String.valueOf(nbValidTickets);
    }

    public String countExpiredTickets(List<Ticket> tickets) {
        int nbExpiredTickets = 0;
        for (Ticket ticket : tickets) {
            if (Objects.equals(ticket.getStatus(), "expired")) {
                nbExpiredTickets = nbExpiredTickets + 1 ;
            }
        }
        return String.valueOf(nbExpiredTickets);
    }

    public String countRefundedTickets(List<Ticket> tickets) {
        int nbRefundedTickets = 0;
        for (Ticket ticket : tickets) {
            if (ticket.getIs_refunded()) {
                nbRefundedTickets = nbRefundedTickets + 1 ;
            }
        }
        return String.valueOf(nbRefundedTickets);
    }


    public void toTicketsManagement(ActionEvent actionEvent) {
        System.out.println("AdminPanelController : toTicketsManagement");
        AppController.getInstance().loadTicketsManagement();
    }
}
