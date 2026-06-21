package services;

import dao.SeanceDAO;
import dao.TicketDAO;
import models.Seance;
import models.Ticket;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.UUID;

public class TicketService {

    public static boolean buyTicket(String code, String title, int userId, int seanceId, String type, float price, String status, Timestamp usedAt, boolean isRefunded) {
        Ticket ticket = new Ticket(null, title, userId, seanceId, type, price, "available", null, false);
        int ticketId = TicketDAO.insertRow(ticket);

        if (ticketId != -1) {
            System.out.println("SetCode pour le ticket");
            ticket.setId(ticketId);
            String qrUrl = generateQRCode(ticketId, seanceId);
            ticket.setCode(qrUrl);
            return ticket.update();
        }
        return false;
    }

    public static void refundTicket(Ticket ticket) {
        ticket.setStatus("expired");
        ticket.setIs_refunded(true);
        ticket.update();
    }

    public static void setTicketStatus(Ticket ticket) {
        Seance seance = SeanceDAO.getRowById(ticket.getSeance_id());
        if (Objects.equals(ticket.getStatus(), "used")) {
            ticket.setStatus("used");
        } else if (seance.getDate().before(new java.util.Date()) || ticket.getIs_refunded()) {
            ticket.setStatus("expired");
        }
        ticket.update();
    }

    public static String generateQRCode(int ticketId, int seanceId) {
        // UUID unique + infos ticket encodées
        String data = "TEASY-TICKET-" + ticketId + "-" + seanceId + "-" + UUID.randomUUID().toString().substring(0, 8);
        return "https://api.qrserver.com/v1/create-qr-code/?size=200x200&data=" +
                URLEncoder.encode(data, StandardCharsets.UTF_8);
    }

    public static void validateQRCode(String scannedData) {
        if (scannedData.startsWith("TEASY-TICKET-")) {
            String[] parts = scannedData.split("-");
            int ticketId = Integer.parseInt(parts[2]);
            int seanceId = Integer.parseInt(parts[3]);
            // valider ticket avec TicketDAO.getRowById(ticketId)
        }
    }

    public static String getTicketCode(String qrURL) throws MalformedURLException {
        String query = new URL(qrURL).getQuery();

        for (String param : query.split("&")) {
            String[] parts = param.split("=", 2);
            String key = parts[0];
            String value = parts.length > 1 ? parts[1] : "";

            if (key.equals("data")) {
                return URLDecoder.decode(value, StandardCharsets.UTF_8);
            }
        }

        return null;
    }
}
