package services;

import dao.TicketDAO;
import models.Ticket;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
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
            return TicketDAO.updateRowById(ticket);
        }
        return false;
    }

    private static String generateQRCode(int ticketId, int seanceId) {
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
}
