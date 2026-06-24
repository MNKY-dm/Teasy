package services;

import dao.TicketDAO;
import models.Seance;
import models.enums.SeanceStatus;
import utils.TypeConverter;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SeanceService {
    private static final SeanceStatusCache statusCache = SeanceStatusCache.getInstance();

    public static void prepareSeancesForDisplay(List<Seance> seances) {
        if (seances == null || seances.isEmpty()) {
            return;
        }

        List<Integer> seanceIds = seances.stream()
                .map(Seance::getId)
                .collect(Collectors.toList());

        Map<Integer, Integer> ticketsCountBySeance = TicketDAO.countBySeanceIds(seanceIds);
        Date now = new Date();

        for (Seance seance : seances) {
            int usedTickets = ticketsCountBySeance.getOrDefault(seance.getId(), 0);
            int remainingPlaces = computeRemainingPlaces(seance, usedTickets);
            SeanceStatus status = computeSeanceStatus(seance, remainingPlaces, now);

            seance.setRemainingPlaces(remainingPlaces);
            seance.setStatus(status);
            seance.setFormattedDate(TypeConverter.dateFormat(seance.getDate()));

            statusCache.put(seance.getId(), status);
        }
    }

    public static int getRemainingPlaces(Seance seance) {
        if (seance.getRemainingPlaces() != null) {
            return seance.getRemainingPlaces();
        }

        int remainingPlaces = computeRemainingPlaces(seance, TicketDAO.countBySeance(seance));
        seance.setRemainingPlaces(remainingPlaces);
        return remainingPlaces;
    }

    public static SeanceStatus getSeanceStatus(Seance seance) {
        if (seance.getStatus() != null) {
            return seance.getStatus();
        }

        SeanceStatus cachedStatus = statusCache.get(seance.getId());
        if (cachedStatus != null) {
            seance.setStatus(cachedStatus);
            return cachedStatus;
        }

        SeanceStatus status = computeSeanceStatus(seance, getRemainingPlaces(seance), new Date());
        seance.setStatus(status);
        statusCache.put(seance.getId(), status);
        return status;
    }

    public static boolean isAvailable(Seance seance) {
        return getSeanceStatus(seance).equals(SeanceStatus.DISPONIBLE);
    }

    private static int computeRemainingPlaces(Seance seance, int usedTickets) {
        int nbPlaces = seance.getNb_places() != null ? seance.getNb_places() : 0;
        return Math.max(0, nbPlaces - usedTickets);
    }

    private static SeanceStatus computeSeanceStatus(Seance seance, int remainingPlaces, Date now) {
        if (seance.getIs_cancelled()) {
            return SeanceStatus.ANNULE;
        } else if (remainingPlaces == 0) {
            return SeanceStatus.COMPLET;
        } else if (seance.getDate() != null && seance.getDate().before(now)) {
            return SeanceStatus.EXPIRE;
        } else {
            return SeanceStatus.DISPONIBLE;
        }
    }
}
