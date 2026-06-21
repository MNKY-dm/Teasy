package models;

import dao.SeanceDAO;

import java.sql.Timestamp;

public class Seance {

    private Integer id;
    private Integer event_id;
    private Timestamp date;
    private String location;
    private Integer nb_places;
    private boolean is_cancelled;
    private Timestamp created_at;

    public Seance(Integer event_id, Timestamp date, String location, Integer nb_places, boolean is_cancelled) {
        this.event_id = event_id;
        this.date = date;
        this.location = location;
        this.nb_places = nb_places;
        this.is_cancelled = is_cancelled;
    }

    public void update() {
        SeanceDAO.updateRowById(this);
    }

    public void delete() {
        SeanceDAO.deleteRowById(this.id);
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

    public boolean getIs_cancelled() {
        return this.is_cancelled;
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
            throw new IllegalArgumentException("La date ne peut pas être null.");
        }
    }

    public void setLocation(String location) {
        if (location != null) {
            this.location = location;
        }
        else {
            throw new IllegalArgumentException("L'emplacement ne peut pas être null.");
        }
    }

    public void setNb_places(Integer nb_places) {
        this.nb_places = nb_places; // Peut être null (voir BDD)
    }

    public void setIs_cancelled(boolean is_cancelled) {
        this.is_cancelled = is_cancelled;
    }


    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }
}
