package models;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Seance {

    private Integer id;
    private Integer event_id;
    private Timestamp date;
    private String location;
    private Integer nb_places;
    private String statut;
    private Timestamp created_at;

    public Seance(Integer id, Integer event_id, Timestamp date, String location, Integer nb_places, String statut, Timestamp created_at) {
        this.id = id;
        this.event_id = event_id;
        this.date = date;
        this.location = location;
        this.nb_places = nb_places;
        this.statut = statut;
        this.created_at = created_at; // Récupère le timestamp dès l'instant où la variable est créée.
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

    public String getLocation() {
        return this.location;
    }

    public Integer getNb_places() {
        return this.nb_places;
    }

    public String getStatut() {
        return this.statut;
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
        this.statut = statut;
    }

    public void setCreated_at(String created_at) {
        this.created_at = new Timestamp(System.currentTimeMillis()); // Peut être null (voir BDD)
    }
}
