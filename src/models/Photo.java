package models;

import java.net.HttpURLConnection;
import java.net.URL;
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

    // Méthode qui permet de vérifier si l'URL est valide (si elle ne renvoie pas vers une page introuvable), via une requête HTTP
    public boolean isUrlValid() {
        String urlString = this.url ;
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
