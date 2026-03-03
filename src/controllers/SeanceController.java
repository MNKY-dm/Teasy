package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import models.Event;
import models.Seance;
import models.User;
import services.SessionManager;

import java.net.URL;
import java.util.ResourceBundle;

public class SeanceController implements Initializable {
    private int seanceId;

    @FXML
    private Label eventTitle;

    @FXML
    private Label labelPrice1;

    @FXML
    private Label labelPrice2;

    @FXML
    private Label labelPrice3;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("[SEANCECONTROLLER] : initializing");
        // Récupérer l'utilisateur connecté de la session
        User currentUser = SessionManager.getInstance().getCurrentUser();

        // Débugger
        System.out.println("initialize() appelée");
//        System.out.println("event : " + event.toString());
        System.out.println("seanceId = 0 ? " + (seanceId == 0));
        System.out.println("eventTitle null ? " + (eventTitle == null));

        // Vérifier qu'on est bien connecté (par sécurité)
        if (currentUser == null) {
            System.out.println("Erreur dans SeanceController : Aucun utilisateur connecté.");
            AppController.getInstance().loadLogin();
            return; // interrompt la fonction si pas d'utilisateur connecté
        }

        System.out.println("SeanceController : Séance chargée pour : " + currentUser.getEmail());
    }

    @FXML
    public void setSeance(Seance seance, Event event) {
        this.seanceId = seance.getId();
        eventTitle.setText(event.getName());
//        labelPrice1.setText();
//        labelPrice2.setText();
//        labelPrice3.setText();
    }
}
