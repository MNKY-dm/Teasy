package controllers;

import dao.EventDAO;
import dao.TicketDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.Seance;
import models.Ticket;
import services.SessionManager;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class TicketsManagementController implements Initializable {
    private Ticket currentTicket;
    private HBox currentTicketPane;

    @FXML
    private VBox ticketsRoot;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Débugger
        System.out.println("initialize() appelée");
    }

    public void loadTicketsInfos() {

        System.out.println("loadTicketsInfos est bien lancée.");
        // Récupérer les séances de l'événement courant
        List<Ticket> tickets = TicketDAO.getAll();

        System.out.println("Nombre de tickets trouvés : " + tickets.size());
        // Afficher chaque événement dans une card
        for (Ticket ticket : tickets) {
            addTicketInfos(ticket);
            System.out.println("Ticket affiché : " + ticket.getId());
        }
    }

    @FXML
    public void addTicketInfos(Ticket ticket) {
        System.out.println("addTicketsInfos pour l'event : " + ticket.getId());
        try {
            // Charger le FXML de la card
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/TicketInfos.fxml"));
            HBox cardRoot = loader.load();

            // Charger le controller et set up les infos de la card selon l'event
            TicketInfosController ticketInfosController = loader.getController();
            ticketInfosController.setTicketsInfos(ticket.getId());

            cardRoot.setOnMouseClicked(event -> {
                ticketClicked(cardRoot, ticket);
            });

            // L'ajouter au HBOX
            ticketsRoot.getChildren().add(cardRoot);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setTickets() {
        loadTicketsInfos();
    }

    @FXML
    private void ticketClicked(HBox cardRoot, Ticket ticket) {
        if (currentTicketPane != null) {
            currentTicketPane.setStyle("-fx-background-color: #111113; -fx-text-fill: #FFFFFF;");
        }
        currentTicketPane = cardRoot;
        currentTicketPane.setStyle("-fx-background-color: #5B4FFF; -fx-text-fill: #111113;");
        currentTicket = ticket;
    }

    @FXML
    private void updateTicketsInfos() {
        System.out.println("updateTicketsInfos pour l'event : " + currentTicket.getId());
        AppController.getInstance().loadTicketModifier(currentTicket);
    }

    @FXML
    private void deleteTicket() {
        try {
            currentTicket.delete();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

}
