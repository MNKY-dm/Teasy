package models;

import dao.EventDAO;

import java.net.URL;
import java.sql.Timestamp;

public class Event {
    private Integer id;
    private String name;
    private String description;
    private String affiche;
    private String language;
    private Integer creator_id;
    private Timestamp created_at;

    public Event(String name, String description, String affiche, String language, Integer creator_id) {
        this.name = name;
        this.description = description;
        this.affiche = affiche;
        this.language = language;
        this.creator_id = creator_id;
    }

    public void update() {
        EventDAO.updateRowById(this);
    }

    public void delete() {
        EventDAO.deleteRowById(this.id);
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

    public Integer getCreator_id() {
        return this.creator_id;
    }

    public Timestamp getCreated_at() {
        return this.created_at;
    }

    public void setId(int id) {
        if (id >= 0) {
            this.id = id;
        } else {
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
            throw new IllegalArgumentException("La description ne peut pas être null.");
        }
    }

    public void setAffiche(String affiche) {
        if (affiche != null) {
            this.affiche = affiche;
        } else {
            throw new IllegalArgumentException("Le mot de passe ne peut pas être null.");
        }
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setCreator_id(Integer creator_id) {
        this.creator_id = creator_id;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public boolean isAfficheUrlValid() {
        String urlString = this.affiche;
        if (urlString == null || urlString.trim().isEmpty()) {
            return false;
        }

        try {
            URL url = new URL(urlString);
            String protocol = url.getProtocol();
            return "http".equalsIgnoreCase(protocol) || "https".equalsIgnoreCase(protocol);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", affiche='" + affiche + '\'' +
                ", language='" + language + '\'' +
                ", créé le='" + created_at + '\'' +
                '}';
    }
}
