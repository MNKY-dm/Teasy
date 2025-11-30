package models;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Photo {

    private Integer id;
    private int event_id;
    private String url;
    private String alt;
    private String type;
    private Timestamp created_at;

    public Photo(int event_id, String url, String alt, String type) {
        this.event_id = event_id;
        this.url = url;
        this.alt = alt;
        this.type = type;
    }

    public int getId() {
        return this.id;
    }

    public int getEvent_id() {
        return this.event_id;
    }

    public String getUrl() {
        return this.url;
    }

    public String getAlt() {
        return this.alt;
    }

    public String getType() {
        return this.type;
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

    public void setEvent_id(int event_id) {
        if (event_id >= 0) {
            this.event_id = event_id;
        } else {
            throw new IllegalArgumentException("L'url ne peut pas être null.");
        }
    }

    public void setUrl(String url) {
        if (url != null) {
            this.url = url;
        } else {
            throw new IllegalArgumentException("L'url ne peut pas être null.");
        }
    }

    public void setAlt(String alt) {
        this.alt = alt; // Peut être null (voir BDD)
    }

    public void setType(String type) {
        this.type = type; // Peut être null (voir BDD)
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at; // Peut être null (voir BDD)
    }
}
