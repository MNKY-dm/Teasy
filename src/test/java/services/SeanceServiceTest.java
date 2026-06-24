package services;

import dao.TicketDAO;
import models.Seance;
import models.enums.SeanceStatus;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SeanceServiceTest {

    private Seance buildSeance(int id, LocalDateTime date, int nbPlaces, boolean isCancelled) {
        Seance seance = new Seance(
                1,
                Timestamp.valueOf(date),
                "Paris",
                nbPlaces,
                isCancelled
        );
        seance.setId(id);
        return seance;
    }

    @Test
    void prepareSeancesForDisplay_shouldSetStatusAnnule_whenSeanceIsCancelled() {
        Seance seance = buildSeance(101, LocalDateTime.now().plusDays(5), 100, true);

        try (MockedStatic<TicketDAO> ticketDaoMock = Mockito.mockStatic(TicketDAO.class)) {
            ticketDaoMock.when(() -> TicketDAO.countBySeanceIds(List.of(101)))
                    .thenReturn(Map.of(101, 12));

            SeanceService.prepareSeancesForDisplay(List.of(seance));

            assertEquals(SeanceStatus.ANNULE, seance.getStatus());
            assertEquals(88, seance.getRemainingPlaces());
        }
    }

    @Test
    void prepareSeancesForDisplay_shouldSetStatusComplet_whenNoPlacesLeft() {
        Seance seance = buildSeance(102, LocalDateTime.now().plusDays(5), 10, false);

        try (MockedStatic<TicketDAO> ticketDaoMock = Mockito.mockStatic(TicketDAO.class)) {
            ticketDaoMock.when(() -> TicketDAO.countBySeanceIds(List.of(102)))
                    .thenReturn(Map.of(102, 10));

            SeanceService.prepareSeancesForDisplay(List.of(seance));

            assertEquals(SeanceStatus.COMPLET, seance.getStatus());
            assertEquals(0, seance.getRemainingPlaces());
        }
    }

    @Test
    void prepareSeancesForDisplay_shouldSetStatusExpire_whenDateIsPastAndPlacesRemain() {
        Seance seance = buildSeance(103, LocalDateTime.now().minusDays(2), 50, false);

        try (MockedStatic<TicketDAO> ticketDaoMock = Mockito.mockStatic(TicketDAO.class)) {
            ticketDaoMock.when(() -> TicketDAO.countBySeanceIds(List.of(103)))
                    .thenReturn(Map.of(103, 10));

            SeanceService.prepareSeancesForDisplay(List.of(seance));

            assertEquals(SeanceStatus.EXPIRE, seance.getStatus());
            assertEquals(40, seance.getRemainingPlaces());
        }
    }

    @Test
    void prepareSeancesForDisplay_shouldSetStatusDisponible_whenFutureAndPlacesRemain() {
        Seance seance = buildSeance(104, LocalDateTime.now().plusDays(3), 80, false);

        try (MockedStatic<TicketDAO> ticketDaoMock = Mockito.mockStatic(TicketDAO.class)) {
            ticketDaoMock.when(() -> TicketDAO.countBySeanceIds(List.of(104)))
                    .thenReturn(Map.of(104, 20));

            SeanceService.prepareSeancesForDisplay(List.of(seance));

            assertEquals(SeanceStatus.DISPONIBLE, seance.getStatus());
            assertEquals(60, seance.getRemainingPlaces());
        }
    }

    @Test
    void prepareSeancesForDisplay_shouldNeverSetNegativeRemainingPlaces() {
        Seance seance = buildSeance(105, LocalDateTime.now().plusDays(1), 5, false);

        try (MockedStatic<TicketDAO> ticketDaoMock = Mockito.mockStatic(TicketDAO.class)) {
            ticketDaoMock.when(() -> TicketDAO.countBySeanceIds(List.of(105)))
                    .thenReturn(Map.of(105, 12));

            SeanceService.prepareSeancesForDisplay(List.of(seance));

            assertEquals(0, seance.getRemainingPlaces());
            assertEquals(SeanceStatus.COMPLET, seance.getStatus());
        }
    }
}
