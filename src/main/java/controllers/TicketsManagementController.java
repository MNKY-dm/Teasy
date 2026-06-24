package controllers;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
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
    private long ticketsLoadVersion;

    @FXML
    private VBox ticketsRoot;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("TicketsManagementController.initialize()");
    }

    public void setTickets() {
        loadTicketsInfos();
    }

    public void loadTicketsInfos() {
        final long loadVersion = ++ticketsLoadVersion;

        currentTicket = null;
        currentTicketPane = null;
        ticketsRoot.getChildren().setAll(new Label("Chargement des tickets..."));

        Task<List<Ticket>> task = new Task<>() {
            @Override
            protected List<Ticket> call() {
                return TicketService.getTicketsWithSeanceInfos(false, -1);
            }
        };

        task.setOnSucceeded(workerStateEvent -> {
            if (loadVersion != ticketsLoadVersion) {
                return;
            }

            List<Ticket> tickets = task.getValue();
            ticketsRoot.getChildren().clear();

            if (tickets == null || tickets.isEmpty()) {
                ticketsRoot.getChildren().setAll(new Label("Aucun ticket à afficher."));
                return;
            }

            for (Ticket ticket : tickets) {
                addTicketInfos(ticket);
            }
        });

        task.setOnFailed(workerStateEvent -> {
            if (loadVersion != ticketsLoadVersion) {
                return;
            }

            ticketsRoot.getChildren().setAll(new Label("Impossible de charger les tickets."));
            Throwable exception = task.getException();
            if (exception != null) {
                exception.printStackTrace();
            }
        });

        Thread thread = new Thread(task, "tickets-management-loader");
        thread.setDaemon(true);
        thread.start();
    }

    public void addTicketInfos(Ticket ticket) {
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
        if (currentTicket == null) {
            System.out.println("Aucun ticket sélectionné.");
            return;
        }

        AppController.getInstance().loadTicketModifier(currentTicket);
    }

    @FXML
    private void deleteTicket() {
        if (currentTicket == null) {
            System.out.println("Aucun ticket sélectionné.");
            return;
        }

        currentTicket.delete();

        if (currentTicketPane != null) {
            ticketsRoot.getChildren().remove(currentTicketPane);
        }

        currentTicket = null;
        currentTicketPane = null;
    }

    public void moveToAdminPanel(ActionEvent actionEvent) {
        AppController.getInstance().loadAdminPanel();
    }
}
