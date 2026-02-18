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

    public void setSeanceInfos(Seance seance){
        System.out.println("setSeanceInfos pour l'event : " + seance.getEvent_id());

        try {
            // Tenter de récupérer l'ID de l'événement courant
            this.eventId = seance.getEvent_id();

//            List<Seance>  seances = EventDAO.getSeances(eventId); // Garder la trace des erreurs

            // Tenter de récupérer les seances liées à l'événement
            // Si on trouve une/des seance(s) liée(s) à cet événement

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

        } catch (Exception e) {
            System.err.println("EventCardController : erreur dans setSeanceInfos ; Impossible de récupérer les infos de l'événement " + e.getMessage());
        }
    }
}
