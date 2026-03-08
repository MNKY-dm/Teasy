package controllers;

import dao.PricingDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import models.Event;
import models.Pricing;
import models.Seance;
import models.User;
import services.SessionManager;

import java.net.URL;
import java.util.ResourceBundle;

import static utils.TypeConverter.StringToFloat;

public class SeanceController implements Initializable {
    private int seanceId;
    private Event event;
    private User currentUser;

    @FXML
    private Label eventTitle;

    @FXML
    private Label labelPrice1;

    @FXML
    private Label labelPrice2;

    @FXML
    private Label labelPrice3;

    @FXML
    private Spinner<Integer> spinnerNbTickets1;

    @FXML
    private Spinner<Integer> spinnerNbTickets2;

    @FXML
    private Spinner<Integer> spinnerNbTickets3;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("[SEANCECONTROLLER] : initializing");
        // Récupérer l'utilisateur connecté de la session
        this.currentUser = SessionManager.getInstance().getCurrentUser();

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

        setSpinners();

        System.out.println("SeanceController : Séance chargée pour : " + currentUser.getEmail());
    }

    @FXML
    public void setSeance(Seance seance, Event event) {
        this.seanceId = seance.getId();
        this.event = event;
        Pricing pricing = PricingDAO.getRowBySeanceId(seanceId);
        eventTitle.setText(event.getName());
        labelPrice1.setText(pricing.getPrice1() + "");
        labelPrice2.setText(pricing.getPrice2() + "");
        labelPrice3.setText(pricing.getPrice3() + "");
    }

    @FXML
    public void setSpinners() {
        System.out.println("[SEANCECONTROLLER] : setSpinners");
        spinnerNbTickets1.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0, 1));
        spinnerNbTickets2.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0, 1));
        spinnerNbTickets3.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0, 1));
    }

    @FXML
    public void returnToEvent() {
        System.out.println("[SEANCECONTROLLER] : returnToEvent");
        AppController.getInstance().loadEvent(event);
    }

    @FXML
    public void buyTickets(ActionEvent actionEvent) {
        System.out.println("[SEANCECONTROLLER] : buyTickets");
        int nbTickets1 = spinnerNbTickets1.getValue();
        int nbTickets2 = spinnerNbTickets2.getValue();
        int nbTickets3 = spinnerNbTickets3.getValue();

        for (int i = 0; i < nbTickets1; i++) {
            services.TicketService.buyTicket(null, event.getName(), currentUser.getId(), seanceId, "STANDARD", StringToFloat(labelPrice1.getText()), "valid", null, false);
            System.out.println("[SEANCECONTROLLER] buyTickets : ticket STANDARD n°" + i);
        }
        for (int i = 0; i < nbTickets2; i++) {
            services.TicketService.buyTicket(null, event.getName(), currentUser.getId(), seanceId, "PLUS", StringToFloat(labelPrice2.getText()), "valid", null, false);
            System.out.println("[SEANCECONTROLLER] buyTickets : ticket PLUS n°" + i);
        }
        for (int i = 0; i < nbTickets3; i++) {
            services.TicketService.buyTicket(null, event.getName(), currentUser.getId(), seanceId, "VIP", StringToFloat(labelPrice3.getText()), "valid", null, false);
            System.out.println("[SEANCECONTROLLER] buyTickets : ticket VIP n°" + i);
        }
    }

    // Tests
    static void main(String[] args) {
        Pricing pricing = PricingDAO.getRowBySeanceId(3);
        System.out.println(pricing.getPrice1()+ " " + pricing.getPrice2() + " " + pricing.getPrice3() + " ");
    }
}
