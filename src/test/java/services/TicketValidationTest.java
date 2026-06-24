package services;

import dao.SeanceDAO;
import dao.TicketDAO;
import models.Seance;
import models.Ticket;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TicketValidationTest {

    @Test
    void setTicketStatus_shouldKeepUsedStatus_whenTicketIsAlreadyValidated() {
        Ticket ticket = new Ticket(
                "code-demo",
                "Concert test",
                1,
                10,
                "standard",
                29.99f,
                "used",
                Timestamp.valueOf(LocalDateTime.now()),
                false
        );
        ticket.setId(1);

        Seance seance = new Seance(
                1,
                Timestamp.valueOf(LocalDateTime.now().plusDays(2)),
                "Paris",
                100,
                false
        );
        seance.setId(10);

        try (MockedStatic<SeanceDAO> seanceDaoMock = Mockito.mockStatic(SeanceDAO.class);
             MockedStatic<TicketDAO> ticketDaoMock = Mockito.mockStatic(TicketDAO.class)) {

            seanceDaoMock.when(() -> SeanceDAO.getRowById(10)).thenReturn(seance);

            TicketService.setTicketStatus(ticket);

            assertEquals("used", ticket.getStatus());

            ticketDaoMock.verify(() -> TicketDAO.updateRowById(ticket));
        }
    }
}
