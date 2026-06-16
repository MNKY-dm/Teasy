package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import models.Ticket;
import services.TicketService;

import java.net.MalformedURLException;

public class TicketModifierController {
    @FXML public Label ticketTitle;

    @FXML public ImageView ticketCode;
    @FXML public TextField ticketId;
    @FXML public TextField ticketCodeField;
    @FXML public TextField ticketLabel;
    @FXML public TextField ticketUserId;
    @FXML public TextField ticketSeanceId;
    @FXML public ChoiceBox<String> ticketType;
    @FXML public TextField ticketPrice;
    @FXML public ChoiceBox<String> ticketStatus;
    @FXML public CheckBox ticketRefunded;
    @FXML public DatePicker ticketUsedAt;
    @FXML public DatePicker ticketCreatedAt;

    public void setTicket(Ticket ticket)  {
        ticketTitle.setText(ticket.getTitle());

        Image qrCode = new Image(ticket.getCode(), true);
        ticketCode.setImage(qrCode);
        try {
            ticketCodeField.setText(TicketService.getTicketCode(ticket.getCode()));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        ticketLabel.setText(ticket.getTitle());
        ticketLabel.setEditable(false); // car change en fonction du seance_id
        ticketRefunded.setSelected(ticket.getIs_refunded());

        ticketUsedAt.setValue(ticket.getUsed_at().toLocalDateTime().toLocalDate());
        ticketCreatedAt.setValue(ticket.getCreated_at().toLocalDateTime().toLocalDate());
        ticketCreatedAt.setEditable(false);
    }

    public void saveTicket(ActionEvent actionEvent) {

    }

    public void toTicketsManagement(ActionEvent actionEvent) {
        System.out.println("AdminPanelController : toTicketsManagement");
        AppController.getInstance().loadTicketsManagement();
    }

}
