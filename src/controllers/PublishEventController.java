package controllers;

import dao.EventDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import models.Event;

import java.net.URI;
import java.net.URL;
import java.sql.Timestamp;
import java.util.List;
import java.util.ResourceBundle;

public class PublishEventController implements Initializable {
    @FXML public TextField eventName;
    @FXML public TextField eventDescription;
    @FXML public TextField eventAfficheUrl;
    @FXML public ImageView eventAffiche;
    @FXML public TextField eventLanguage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        eventAfficheUrl.textProperty().addListener((obs, oldValue, newValue) -> {
            updateAffichePreview(newValue);
        });
    }

    public void saveEvent() {
        System.out.println("[PUBLISHEVENTCONTROLLER] : creating new event");

        Event newEvent;
        try {
            newEvent = new Event(
                    eventName.getText(),
                    eventDescription.getText(),
                    eventAfficheUrl.getText(), // valeur si int dans ticketSeanceId
                    eventLanguage.getText()
            );

            if (validateValues(newEvent)) {
                newEvent.setCreated_at(new Timestamp(System.currentTimeMillis()));

                EventDAO.insertNewRow(newEvent);
                System.out.println("[PUBLISHEVENTCONTROLLER] : événement créé avec succès");
                moveToHome();
            } else {
                System.out.println("Informations non valides. L'événement n'a pas été créé.");
            }
        } catch (Exception e) {
            // Si erreur détectée, pas de création
            System.out.println("Erreur création de l'évent dans saveEvent.");
            e.printStackTrace();
        }
    }

    public boolean validateValues(Event newEvent) {
        if (newEvent.getName() != null && !newEvent.getName().isEmpty() && newEvent.getDescription() != null && !newEvent.getDescription().isEmpty()) {
            List<Event> events = EventDAO.getAll();
            for (Event event : events) {
                if (event.getName().equals(newEvent.getName())) {
                    System.out.println("Un événement avec le même nom existe déjà.");
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private void updateAffichePreview(String url) {
        if (url == null || url.isBlank()) {
            eventAffiche.setImage(null);
            return;
        }

        if (!isValidHttpUrl(url)) {
            eventAffiche.setImage(null);
            return;
        }

        Image image = new Image(url, true);

        image.progressProperty().addListener((obs, oldProgress, newProgress) -> {
            if (newProgress.doubleValue() >= 1.0) {
                if (!image.isError()) {
                    eventAffiche.setImage(image);
                } else {
                    eventAffiche.setImage(null);
                    System.out.println("Image invalide ou inaccessible : " + image.getException());
                }
            }
        });

        image.errorProperty().addListener((obs, oldError, newError) -> {
            if (Boolean.TRUE.equals(newError)) {
                eventAffiche.setImage(null);
                System.out.println("Erreur de chargement image : " + image.getException());
            }
        });
    }

    private boolean isValidHttpUrl(String value) {
        try {
            URI uri = URI.create(value.trim());

            return uri.getScheme() != null
                    && uri.getHost() != null
                    && (uri.getScheme().equalsIgnoreCase("http")
                    || uri.getScheme().equalsIgnoreCase("https"));
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    @FXML
    public void moveToHome() {
        System.out.println("PublishEventController : moveToHome");
        AppController.getInstance().loadHome();
    }

}
