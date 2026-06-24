package services;

import dao.SeanceDAO;
import dao.TicketDAO;
import dao.UserDAO;
import models.Seance;
import models.Ticket;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.*;

public class TicketService {

    public static List<Ticket> getTicketsWithSeanceInfos(boolean onlyAvailable, int userId) {
        List<Ticket> tickets = TicketDAO.getAll();

        if (userId != -1) {
            tickets = tickets.stream()
                    .filter(t -> t.getUser_id() == userId)
                    .toList();
        }

        if (onlyAvailable) {
            tickets = tickets.stream()
                    .filter(t -> "available".equalsIgnoreCase(t.getStatus()))
                    .toList();
        }

        if (tickets.isEmpty()) {
            return tickets;
        }

        Set<Integer> seanceIds = new HashSet<>();
        Set<Integer> userIds = new HashSet<>();

        for (Ticket t : tickets) {
            seanceIds.add(t.getSeance_id());
            userIds.add(t.getUser_id());
        }

        List<Seance> seances = SeanceDAO.getRowsByIds(new ArrayList<>(seanceIds));
        Map<Integer, String> userNamesById = UserDAO.getNamesByIds(new ArrayList<>(userIds));

        Map<Integer, Seance> seanceById = new HashMap<>();
        for (Seance s : seances) {
            seanceById.put(s.getId(), s);
        }

        for (Ticket t : tickets) {
            Seance s = seanceById.get(t.getSeance_id());
            if (s != null) {
                t.setSeance(s);
            }

            String userName = userNamesById.get(t.getUser_id());
            if (userName != null && !userName.isBlank()) {
                t.setUserName(userName);
            } else {
                t.setUserName("Utilisateur #" + t.getUser_id());
            }
        }

        return tickets;
    }

    public static void buyTicket(String code, String title, int userId, int seanceId, String type, float price, String status, Timestamp usedAt, boolean isRefunded) {
        Ticket ticket = new Ticket(null, title, userId, seanceId, type, price, "available", null, false);
        int ticketId = TicketDAO.insertRow(ticket);

        if (ticketId != -1) {
            System.out.println("SetCode pour le ticket");
            ticket.setId(ticketId);
            String qrUrl = generateQRCode(ticketId, seanceId);
            ticket.setCode(qrUrl);
            ticket.update();
        }
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
        String data = "TEASY-TICKET-" + ticketId + "-" + seanceId + "-" + UUID.randomUUID().toString().substring(0, 8);
        return "https://api.qrserver.com/v1/create-qr-code/?size=200x200&data=" +
                URLEncoder.encode(data, StandardCharsets.UTF_8);
    }

    public static void validateQRCode(String scannedData) {
        if (scannedData.startsWith("TEASY-TICKET-")) {
            String[] parts = scannedData.split("-");
            int ticketId = Integer.parseInt(parts[2]);
            int seanceId = Integer.parseInt(parts[3]);
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
