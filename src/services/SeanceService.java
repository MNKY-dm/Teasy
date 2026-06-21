package services;

import dao.TicketDAO;
import models.Seance;
import models.enums.SeanceStatus;

public class SeanceService {
    public static int getRemainingPlaces(Seance seance) {
        return seance.getNb_places() - TicketDAO.countBySeance(seance);
    }

    public static SeanceStatus getSeanceStatus(Seance seance) {
        if (seance.getIs_cancelled()) {
            return SeanceStatus.ANNULE;
        } else if (getRemainingPlaces(seance) == 0) {
            return SeanceStatus.COMPLET;
        } else {
            return SeanceStatus.DISPONIBLE;
        }
    }

    public static boolean isAvailable(Seance seance) {
        return getSeanceStatus(seance).equals(SeanceStatus.DISPONIBLE);
    }
}
