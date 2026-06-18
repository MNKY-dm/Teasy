package controllers;

import dao.SeanceDAO;
import dao.TicketDAO;
import dao.UserDAO;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import models.Ticket;
import services.TicketService;

import java.net.MalformedURLException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Objects;

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
        ticketType.setValue(ticket.getType());
        ticketPrice.setText(String.valueOf(ticket.getPrice()));
        ticketStatus.setValue(ticket.getStatus());

        ticketRefunded.setSelected(ticket.getIs_refunded());

        if (ticket.getUsed_at() != null) {
            ticketUsedAt.setValue(ticket.getUsed_at().toLocalDateTime().toLocalDate());
        }
        ticketUsedAt.setEditable(false);
        ticketCreatedAt.setValue(ticket.getCreated_at().toLocalDateTime().toLocalDate());
        ticketCreatedAt.setEditable(false);
    }

    public boolean validateUpdates(Ticket newTicket) {
        if (SeanceDAO.getRowById(newTicket.getSeance_id()) != null && UserDAO.getRowById(newTicket.getUser_id()) != null) {
            // Garder le if si jamais il y a autre chose à ajouter comme vérifications  
            return true;
        }
        return false;
    }

    public void saveTicket() {
        System.out.println("saving ticket");

        String url = ticket.getCode();
        if (!Objects.equals(ticketCodeField.getText(), url)) {
            url = TicketService.generateQRCode(ticket.getId(), ticket.getSeance_id());
        }

        Timestamp ticketUsed_at = ticket.getUsed_at();
        LocalDate usedAtDt;
        if (ticketUsed_at != null) {
            usedAtDt = ticketUsed_at.toLocalDateTime().toLocalDate();
            if (!Objects.equals(ticketUsedAt.getValue(), usedAtDt)) {
                ticketUsed_at = Timestamp.valueOf(ticketUsedAt.getValue().atStartOfDay());
            }
        } else {
            if (ticketUsedAt.getValue() != null) {
                ticketUsed_at = Timestamp.valueOf(ticketUsedAt.getValue().atStartOfDay());
            }
        }

        Ticket newTicket;
        try {
            newTicket = new Ticket(
                    url,
                    ticketLabel.getText(),
                    Integer.parseInt(ticketUserId.getText()),
                    Integer.parseInt(ticketSeanceId.getText()), // valeur si int dans ticketSeanceId
                    ticketType.getValue(),
                    Float.parseFloat(ticketPrice.getText()), // valeur si float dans ticketPrice
                    ticketStatus.getValue(),
                    ticketUsed_at,
                    ticketRefunded.isSelected()
            );
        } catch (NumberFormatException e) {
            newTicket = new Ticket(
                    url,
                    ticketLabel.getText(),
                    Integer.parseInt(ticketUserId.getText()),
                    ticket.getSeance_id(), // si pas int, ne rien modifier
                    ticketType.getValue(),
                    ticket.getPrice(), // si pas float, ne rien modifier
                    ticketStatus.getValue(),
                    ticketUsed_at,
                    ticketRefunded.isSelected()
            );
            System.out.println("La séance ou le prix n'ont pas pu être modifiés");
        } catch (Exception e) {
            // Si autre erreur détectée, pas de modification
            e.printStackTrace();
            newTicket = ticket;
            System.out.println("Ticket non modifié");
        }


        if (validateUpdates(newTicket)) {
            newTicket.setId(ticket.getId());
            System.out.println("Ticket updated : "+ TicketDAO.updateRowById(newTicket));
            setTicket(newTicket);
        } else {
            System.out.println("Informations non valides");
        }

    }

    public void deleteTicket() {
        try {
            this.ticket.delete();
            toTicketsManagement();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void toTicketsManagement() {
        System.out.println("TicketModifierController : toTicketsManagement");
        AppController.getInstance().loadTicketsManagement();
    }

    public void main (String[] args) {
        Ticket test = TicketDAO.getRowById(38);

        System.out.println(validateUpdates(test));
    }

}
