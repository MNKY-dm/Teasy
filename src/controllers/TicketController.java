package controllers;

import dao.SeanceDAO;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import models.Seance;
import models.Ticket;
import services.TicketService;

import java.net.MalformedURLException;
import java.sql.SQLException;

public class TicketController {
    @FXML public Label ticketTitle;

    @FXML public ImageView ticketCode;
    @FXML public TextField ticketId;
    @FXML public TextField ticketCodeField;
    @FXML public TextField ticketLabel;
    @FXML public TextField ticketUserId;
    @FXML public TextField ticketSeanceId;
    @FXML public TextField ticketLocation;
    @FXML public TextField ticketDate;
    @FXML public TextField ticketType;
    @FXML public TextField ticketPrice;
    @FXML public TextField ticketStatus;
    @FXML public CheckBox ticketRefunded;
    @FXML public TextField ticketUsedAt;
    @FXML public TextField ticketCreatedAt;
    @FXML public Label userName;

    Ticket ticket;

    public void setTicket(Ticket ticket)  {
        this.ticket = ticket;

        ticketTitle.setText(ticket.getTitle());
        userName.setText(ticket.getUser());

        Image qrCode = new Image(ticket.getCode(), true);
        ticketCode.setImage(qrCode);
        try {
            ticketCodeField.setText(TicketService.getTicketCode(ticket.getCode()));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        ticketId.setText(String.valueOf(ticket.getId()));

        ticketLabel.setText(ticket.getTitle());
        ticketLabel.setEditable(false); // car change en fonction du seance_id

        ticketUserId.setText(String.valueOf(ticket.getUser_id()));

        ticketSeanceId.setText(String.valueOf(ticket.getSeance_id()));
        Seance seance = SeanceDAO.getRowById(ticket.getSeance_id());

        ticketLocation.setText(seance.getLocation());
        ticketDate.setText(seance.dateFormat(seance.getDate()));

        ticketType.setText(ticket.getType());
        ticketPrice.setText(String.valueOf(ticket.getPrice()));
        ticketStatus.setText(ticket.getStatus());

        ticketRefunded.setSelected(ticket.getIs_refunded());

        if (ticket.getUsed_at() != null) {
            ticketUsedAt.setText(ticket.getUsed_at().toLocalDateTime().toLocalDate().toString());
        }
        ticketUsedAt.setEditable(false);
        ticketCreatedAt.setText(ticket.getCreated_at().toLocalDateTime().toLocalDate().toString());
        ticketCreatedAt.setEditable(false);
    }

    public void refundTicket() {
        try {
            this.ticket.setIs_refunded(true);
            this.ticket.update();
            setTicket(this.ticket);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void moveToHome() {
        System.out.println("TicketController : moveToHome");
        AppController.getInstance().loadHome();
    }

}
