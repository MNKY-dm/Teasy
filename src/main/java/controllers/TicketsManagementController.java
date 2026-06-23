package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.Ticket;
import services.TicketService;

import java.io.IOException;
import java.net.URL;
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

        List<Ticket> tickets = TicketService.getTicketsWithSeanceInfos(false, -1);

        System.out.println("Nombre de tickets trouvés : " + tickets.size());

        ticketsRoot.getChildren().clear();

        for (Ticket ticket : tickets) {
            addTicketInfos(ticket);
            System.out.println("Ticket affiché : " + ticket.getId());
        }
    }

    @FXML
    public void addTicketInfos(Ticket ticket) {
        System.out.println("addTicketsInfos pour le ticket : " + ticket.getId());
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/TicketInfos.fxml"));
            HBox cardRoot = loader.load();

            TicketInfosController ticketInfosController = loader.getController();
            ticketInfosController.setTicketsInfos(ticket);

            cardRoot.setOnMouseClicked(event -> ticketClicked(cardRoot, ticket));

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
    private void updateTicketInfos() {
        System.out.println("updateTicketsInfos pour l'event : " + currentTicket.getId());
        AppController.getInstance().loadTicketModifier(currentTicket);
    }

    @FXML
    private void deleteTicket() {
        currentTicket.delete();
        ticketsRoot.getChildren().remove(currentTicketPane);
    }

    public void moveToAdminPanel(ActionEvent actionEvent) {
        System.out.println("TicketManagementController : moveToAdminPanel");
        AppController.getInstance().loadAdminPanel();
    }
}
