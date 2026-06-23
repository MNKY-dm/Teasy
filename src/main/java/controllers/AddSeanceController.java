package controllers;

import dao.PricingDAO;
import dao.SeanceDAO;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import models.Event;
import models.Pricing;
import models.Seance;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class AddSeanceController  {
    @FXML public TextField eventName;
    @FXML public DatePicker seanceDate;
    @FXML public TextField seanceLocation;
    @FXML public TextField seanceNbPlaces;
    @FXML public TextField price1Field;
    @FXML public TextField price2Field;
    @FXML public TextField price3Field;
    private Event event;

    public void setEvent(Event currentEvent) {
        this.event = currentEvent;
        eventName.setText(currentEvent.getName());
        eventName.setEditable(false);
    }

    public void saveSeance() {
        System.out.println("[ADDSEANCECONTROLLER] : creating new seance");

        try {
            if (event == null) {
                System.out.println("[ADDSEANCECONTROLLER] : aucun event courant, annulation.");
                return;
            }

            LocalDate localDate = seanceDate.getValue();
            if (localDate == null) {
                System.out.println("Merci de choisir une date.");
                return;
            }
            LocalDateTime localDateTime = localDate.atTime(19, 30);
            Timestamp seanceTimestamp = Timestamp.valueOf(localDateTime);

            String location = seanceLocation.getText();
            int nbPlaces = Integer.parseInt(seanceNbPlaces.getText().trim());

            Seance newSeance = new Seance(
                    event.getId(),
                    seanceTimestamp,
                    location,
                    nbPlaces,
                    false
            );

            if (validateValues(newSeance)) {
                newSeance.setCreated_at(new Timestamp(System.currentTimeMillis()));

                int seanceId = SeanceDAO.insertNewRow(newSeance);
                if (seanceId <= 0) {
                    System.out.println("Erreur lors de la création de la séance, pricing non créé.");
                    return;
                }

                // Exemple : tu as récupéré les valeurs de prix dans des TextField
                float price1 = Float.parseFloat(price1Field.getText().trim());
                Float price2 = price2Field.getText().isBlank() ? null
                        : Float.parseFloat(price2Field.getText().trim());
                Float price3 = price3Field.getText().isBlank() ? null
                        : Float.parseFloat(price3Field.getText().trim());

                Pricing pricing = new Pricing(seanceId, price1, price2, price3);
                boolean pricingOk = PricingDAO.insertPricing(pricing);

                if (!pricingOk) {
                    System.out.println("Séance créée mais erreur lors de la création du pricing.");
                }

                System.out.println("[ADDSEANCECONTROLLER] : séance + pricing créés avec succès");
                backToEvent();
            } else {
                System.out.println("Informations non valides. La séance n'a pas été créée.");
            }

        } catch (NumberFormatException e) {
            System.out.println("Nombre de places invalide.");
        } catch (Exception e) {
            System.out.println("Erreur lors de la création de la séance.");
            e.printStackTrace();
        }
    }

    public boolean validateValues(Seance newSeance) {
        if (newSeance.getDate() == null) return false;
        if (newSeance.getLocation() == null || newSeance.getLocation().isBlank()) return false;
        if (newSeance.getNb_places() <= 0) return false;

        List<Seance> seances = SeanceDAO.getAll();
        for (Seance s : seances) {
            if (Objects.equals(s.getEvent_id(), newSeance.getEvent_id())
                    && s.getDate().equals(newSeance.getDate())) {
                System.out.println("Une séance pour cet événement existe déjà à cette date.");
                return false;
            }
        }

        return true;
    }

    @FXML
    public void backToEvent() {
        System.out.println("[ADDSEANCECONTROLLER] : backToEvent");
        AppController.getInstance().loadEditEvent(event);
    }

}
