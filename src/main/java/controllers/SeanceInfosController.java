package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import models.Seance;
import models.enums.SeanceStatus;
import services.SeanceService;
import utils.TypeConverter;

public class SeanceInfosController {
    private int eventId;
    private Seance currentSeance;

    @FXML private Label seanceLocation;
    @FXML private Label seanceDate;
    @FXML private Label seanceNbPlaces;
    @FXML private Label seanceStatus;
    @FXML private HBox seanceInfosRoot;

    private boolean disponible;

    public void setSeanceInfos(Seance seance) {
        try {
            this.currentSeance = seance;
            this.eventId = seance.getEvent_id();

            String location = seance.getLocation() != null ? seance.getLocation() : "Lieu inconnu";
            String date = seance.getFormattedDate() != null
                    ? seance.getFormattedDate()
                    : TypeConverter.dateFormat(seance.getDate());

            int nbPlaces = SeanceService.getRemainingPlaces(seance);
            SeanceStatus status = SeanceService.getSeanceStatus(seance);

            this.disponible = status == SeanceStatus.DISPONIBLE;

            seanceLocation.setText(location);
            seanceDate.setText(date);
            seanceNbPlaces.setText(String.valueOf(nbPlaces));
            seanceStatus.setText(status.toString());

        } catch (Exception e) {
            System.err.println("SeanceInfosController : erreur dans setSeanceInfos ; Impossible de récupérer les infos de la séance. " + e.getMessage());
        }
    }

    @FXML
    private void showSeance(MouseEvent event) {
        System.out.println("Seance cliquée : ID événement --> " + eventId + " ; Date de la séance --> " + seanceDate.getText());
        System.out.println("Séance disponible ? --> " + disponible);
    }
}
