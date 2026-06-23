package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.Event;
import services.SessionManager;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MyEventsController implements Initializable {
    private Event currentEvent;
    private HBox currentEventPane;

    @FXML private VBox eventsRoot;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Débugger
        System.out.println("initialize() appelée");
    }

    public void loadEventsInfos() {

        System.out.println("loadEventsInfos est bien lancée.");
        // Récupérer les séances de l'événement courant
        List<Event> events = SessionManager.getInstance().getCurrentUser().getEvents();

        System.out.println("Nombre d'événements trouvés : " + events.size());
        // Afficher chaque événement dans une card
        for (Event event : events) {
            addEventInfos(event);
            System.out.println("Événement affiché : " + event.getId());
        }
    }

    @FXML
    public void addEventInfos(Event event) {
        System.out.println("addEventInfos pour l'event : " + event.getId());
        try {
            // Charger le FXML de la card
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/EventInfos.fxml"));
            HBox cardRoot = loader.load();

            // Charger le controller et set up les infos de la card selon l'event
            EventInfosController eventInfosController = loader.getController();
            eventInfosController.setEventInfos(event.getId());

            cardRoot.setOnMouseClicked(mouseEvent -> {
                eventClicked(cardRoot, event);
            });

            // L'ajouter au HBOX
            eventsRoot.getChildren().add(cardRoot);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setEvents() { loadEventsInfos(); }

    @FXML
    private void eventClicked(HBox cardRoot, Event event) {
        if (currentEventPane != null) {
            currentEventPane.setStyle("-fx-background-color: #111113; -fx-text-fill: #FFFFFF;");
        }
        currentEventPane = cardRoot;
        currentEventPane.setStyle("-fx-background-color: #5B4FFF; -fx-text-fill: #111113;");
        currentEvent = event;
    }

    @FXML
    public void editEvent(ActionEvent actionEvent) {
        System.out.println("MyEventsController : editEvent");
        AppController.getInstance().loadEditEvent(currentEvent);
    }

    @FXML
    public void addSeance(ActionEvent actionEvent) {
        System.out.println("MyEventsController : addSeance");
        AppController.getInstance().loadAddSeance(currentEvent);
    }

    @FXML
    private void showEvent() {
        System.out.println("showEvent pour le ticket : " + currentEvent.getId());
        AppController.getInstance().loadEvent(currentEvent);
    }

    @FXML
    private void deleteEvent() {
        // TODO : ajouter une vérification et confirmation avant toute action sur les Event
        currentEvent.delete();
        eventsRoot.getChildren().remove(currentEventPane);
    }

    public void moveToHome(ActionEvent actionEvent) {
        System.out.println("MyEventsController : moveToHome");
        AppController.getInstance().loadHome();
    }

}
