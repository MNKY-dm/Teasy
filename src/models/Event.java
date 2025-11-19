package models;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Event {
    private Integer id;
    private String name;
    private String description;
    private String affiche;
    private String language;
    private Timestamp created_at;

    public Event(Integer id, String name, String description, String affiche, String language) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.affiche = affiche;
        this.language = language;
        this.created_at = Timestamp.valueOf(LocalDateTime.now()); // Récupère le timestamp dès l'instant où la variable est créée.
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public String getAffiche() {
        return this.affiche;
    }

    public String getLanguage() {
        return this.language;
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

    public void setName(String name) {
        if (name != null) {
            this.name = name;
        } else {
            throw new IllegalArgumentException("Le name ne peut pas être null.");
        }
    }

    public void setDescription(String description) {
        if (description != null) {
            this.description = description;
        } else {
            throw new IllegalArgumentException("La description ne peut pas être null.");
        }
    }

    public void setAffiche(String affiche) {
        if (affiche != null) {
            this.affiche = affiche;
        }
        else {
            throw new IllegalArgumentException("Le mot de passe ne peut pas être null.");
        }
    }

    public void setLanguage(String language) {
        this.language = language; // Peut être null (voir BDD)
    }

    public void setCreated_at(String created_at) {
        this.created_at = new Timestamp(System.currentTimeMillis()); // Peut être null (voir BDD)
    }
}
