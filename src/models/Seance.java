package models;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Seance {

    private Integer id;
    private Integer event_id;
    private Timestamp date;
    private String location;
    private Integer nb_places;
    private String status;
    private Timestamp created_at;

    public Seance(Integer event_id, Timestamp date, String location, Integer nb_places, String status) {
        this.event_id = event_id;
        this.date = date;
        this.location = location;
        this.nb_places = nb_places;
        this.status = status;
    }

    public int getId() {
        return this.id;
    }

    public Integer getEvent_id() {
        return this.event_id;
    }

    public Timestamp getDate() {
        return this.date;
    }

    public String dateFormat(Timestamp date) {
        if (date == null) {
            return "Date inconnue";
        }

        // Format sans secondes : juste jour/mois/année heure:minute
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return date.toLocalDateTime().format(formatter);
    }

    public String getLocation() {
        return this.location;
    }

    public Integer getNb_places() {
        return this.nb_places;
    }

    public String getStatus() {
        return this.status;
    }

    public Timestamp getCreated_at() {
        return this.created_at;
    }

    public void setId(int id) {
        if (id >= 0) { // Affecter un id seulement s'il n'est pas négatif
            this.id = id;
        }
        else {
            throw new IllegalArgumentException("L'id ne peut pas être négatif.");
        }
    }

    public void setEvent_id(Integer event_id) {
        if (event_id != null) {
            this.event_id = event_id;
        } else {
            throw new IllegalArgumentException("Le event_id ne peut pas être null.");
        }
    }

    public void setDate(Timestamp date) {
        if (date != null) {
            this.date = date;
        } else {
            throw new IllegalArgumentException("L'date ne peut pas être null.");
        }
    }

    public void setLocation(String location) {
        if (location != null) {
            this.location = location;
        }
        else {
            throw new IllegalArgumentException("Le mot de passe ne peut pas être null.");
        }
    }

    public void setNb_places(Integer nb_places) {
        this.nb_places = nb_places; // Peut être null (voir BDD)
    }

    public void setStatut(String statut) {
        this.status = statut;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public static void main (String[] args) {
//        Timestamp now = new Timestamp(1764541539);
//        Seance seance = new Seance(1, now, "chez toi", 2, "epuisé");
//
//        System.out.println(seance.dateFormat(seance.getDate()));
    }
}
