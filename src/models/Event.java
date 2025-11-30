package models;

import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Event {
    private Integer id;
    private String name;
    private String description;
    private String affiche;
    private String language;
    private Timestamp created_at;

    public Event(String name, String description, String affiche, String language) {
        this.name = name;
        this.description = description;
        this.affiche = affiche;
        this.language = language;
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

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public boolean isAfficheUrlValid() {
        String urlString = this.affiche ;
        if (urlString == null || urlString.trim().isEmpty()) {
            return false;
        }

        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Utiliser HEAD pour vérifier sans télécharger l'image
            connection.setRequestMethod("HEAD");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            // Certains serveurs n'acceptent pas HEAD, essayer GET
            connection.setInstanceFollowRedirects(true);

            int responseCode = connection.getResponseCode();

            // Afficher le code de réponse pour déboguer
            System.out.println("URL : " + urlString + " -> Code : " + responseCode);

            // Vérifier si c'est une réponse valide (200-299)
            boolean isValid = responseCode >= 200 && responseCode < 300;
            System.out.println("URL : " + urlString + " -> Status : " + isValid);

            connection.disconnect();
            System.out.println("Valide au retour ? : " + isValid);
            return isValid;

        } catch (Exception e) {
            System.err.println("Erreur vérification image : " + e.getMessage());
            return false;
        }
    }
}
