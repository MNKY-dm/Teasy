package controllers;

import dao.EventDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import models.Event;
import models.Photo;
import models.Seance;

import java.sql.Timestamp;
import java.util.List;

import static java.lang.String.valueOf;

public class SeanceInfosController {

    private int eventId;

    @FXML
    private Label seanceLocation;

    @FXML
    private Label seanceDate;

    @FXML
    private Label seanceNbPlaces;

    @FXML
    private Label seanceStatus;

    @FXML
    private AnchorPane seanceInfosRoot;

    public void setSeanceInfos(Event event){

        try {
            // Tenter de récupérer les infos de l'événement voulu
            this.eventId = event.getId();

            List<Seance>  seances = EventDAO.getSeances(eventId);

            // Tenter de récupérer les seances liées à l'événement
            if (!seances.isEmpty()) { // Si on trouve une/des seance(s) liée(s) à cet événement

                Seance seance = seances.getFirst(); // On récupère la première seance
                String location = seance.getLocation(); // On récupère le lieu de cette seance
                String date = seance.dateFormat(seance.getDate());
                int nbPlaces = seance.getNb_places();
                String status = seance.getStatus();
                try {
                    System.out.println("lieu : " + location);
                    seanceLocation.setText(location); // On l'affiche dans le conteneur FXML seanceLocation
                    seanceDate.setText(date);
                    seanceNbPlaces.setText(valueOf(nbPlaces));
                    seanceStatus.setText(status);
                } catch (Exception ex) {
                    System.err.println("Erreur chargement seance : " + ex.getMessage());
                }
            }

        } catch (Exception e) {
            System.err.println("EventCardController : erreur dans setEventData ; Impossible de récupérer les infos de l'événement " + e.getMessage());
        }
    }
}
