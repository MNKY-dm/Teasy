package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import models.Seance;
import models.enums.SeanceStatus;
import services.SeanceService;
import services.SeanceStatusCache;
import utils.TypeConverter;

import static java.lang.String.valueOf;

public class SeanceInfosController {
    private int eventId;
    @FXML private Label seanceLocation;
    @FXML private Label seanceDate;
    @FXML private Label seanceNbPlaces;
    @FXML private Label seanceStatus;
    @FXML private HBox seanceInfosRoot;
    private final SeanceStatusCache statusCache = SeanceStatusCache.getInstance();
    boolean disponible;

    public void setSeanceInfos(Seance seance){
        System.out.println("setSeanceInfos pour l'event : " + seance.getEvent_id());

        try {
            // Tenter de récupérer l'ID de l'événement courant
            this.eventId = seance.getEvent_id();

            String location = seance.getLocation(); // On récupère le lieu de cette seance
            String date = TypeConverter.dateFormat(seance.getDate());
            int nbPlaces = SeanceService.getRemainingPlaces(seance);
            SeanceStatus status = statusCache.getOrCompute(
                    seance.getId(),
                    () -> SeanceService.getSeanceStatus(seance)
            );
            this.disponible = status == SeanceStatus.DISPONIBLE;
            try {
                System.out.println("lieu : " + location);
                seanceLocation.setText(location); // On l'affiche dans le conteneur FXML seanceLocation
                seanceDate.setText(date);
                seanceNbPlaces.setText(valueOf(nbPlaces));
                seanceStatus.setText(status.toString());
            } catch (Exception ex) {
                System.err.println("Erreur chargement seance : " + ex.getMessage());
            }

        } catch (Exception e) {
            System.err.println("EventController : erreur dans setSeanceInfos ; Impossible de récupérer les infos des séances de l'événement. " + e.getMessage());
        }
    }

    @FXML
    private void showSeance(MouseEvent event){
        System.out.println("Seance cliquée : ID événement --> " + eventId + " ; " + "Date de la séance --> " + seanceDate.getText());
        System.out.println("Séance disponible ? --> " + disponible);
    }
}
