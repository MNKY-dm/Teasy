package controllers;

import dao.EventDAO;
import dao.UserDAO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import models.Event;
import models.User;
import services.SessionManager;

import java.net.URI;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ResourceBundle;

public class EditEventController implements Initializable {
    @FXML public TextField eventName;
    @FXML public TextField eventDescription;
    @FXML public TextField eventAfficheUrl;
    @FXML public ImageView eventAffiche;
    @FXML public TextField eventLanguage;
    @FXML public TextField eventCreator;
    @FXML public TextField eventCreatorId;
    @FXML public Button saveEventBtn;
    private Event event;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        eventAfficheUrl.textProperty().addListener((obs, oldValue, newValue) -> {
            updateAffichePreview(newValue);
        });
    }

    public void setEvent(Event event){
        this.event = event;
        eventName.setText(event.getName());
        eventDescription.setText(event.getDescription());
        eventAfficheUrl.setText(event.getAffiche());
        updateAffichePreview(event.getAffiche());
        if (!event.getLanguage().isEmpty() && event.getLanguage() != null) {
            eventLanguage.setText(event.getLanguage());
        }

        User creator = UserDAO.getRowById(event.getCreator_id());
        eventCreator.setText(creator.getNom());

        eventCreatorId.setText(creator.getId() + "");
        if (!SessionManager.getInstance().getCurrentUser().isAdmin()){
            eventCreatorId.setEditable(false);
        }
    }

    public void saveEvent() {
        System.out.println("[EDITEVENTCONTROLLER] : editing event");

        Event newEvent;
        try {
            newEvent = new Event(
                    eventName.getText(),
                    eventDescription.getText(),
                    eventAfficheUrl.getText(),
                    eventLanguage.getText(),
                    Integer.valueOf(eventCreatorId.getText())
            );

            if (validateValues(newEvent)) {
                newEvent.setCreated_at(new Timestamp(System.currentTimeMillis()));
                newEvent.setId(event.getId());

                EventDAO.updateRowById(newEvent);
                System.out.println("[EDITEVENTCONTROLLER] : événement mis à jour avec succès");
                setEvent(newEvent);
//                moveToHome();
            } else {
                System.out.println("Informations non valides. L'événement n'a pas été mis à jour.");
            }
        } catch (Exception e) {
            // Si erreur détectée, pas de création
            System.out.println("Erreur création de l'évent dans saveEvent.");
            e.printStackTrace();
        }
    }

    public boolean validateValues(Event newEvent) {
        if (newEvent.getName() != null && !newEvent.getName().isEmpty() && newEvent.getDescription() != null && !newEvent.getDescription().isEmpty()) {
            if (EventDAO.getRowByName(newEvent.getName()) == null) {
                System.out.println("Un événement avec le même nom existe déjà.");
                return false;
            } else if (eventAfficheUrl.getText() == null || eventAfficheUrl.getText().isEmpty() || !isValidHttpUrl(eventAfficheUrl.getText())) {
                return false;
            }
            return true;
        }
        return false;
    }

    public void updateCreatorPreview() {
        String text = eventCreatorId.getText().trim();
        if (text.isEmpty()) {
            eventCreator.setText("");
            return;
        }

        try {
            int creatorId = Integer.parseInt(text);

            User u = UserDAO.getRowById(creatorId);
            if (u != null) {
                eventCreator.setText(u.getNom());
                saveEventBtn.setDisable(false);
            } else {
                saveEventBtn.setDisable(true);
                eventCreator.setText("Aucun utilisateur avec cet ID");
            }

        } catch (NumberFormatException e) {
            saveEventBtn.setDisable(true);
            eventCreator.setText("ID invalide");
        }
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
    public void moveToMyEvents() {
        System.out.println("[EDITEVENTCONTROLLER] : moveToMyEvents");
        AppController.getInstance().loadMyEvents();
    }

}
