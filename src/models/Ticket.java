package models;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Ticket {

    private Integer id;
    private String code;
    private String title;
    private String place;
    private int user_id;
    private int seance_id;
    private String type;
    private float price;
    private String status;
    private Timestamp used_at;
    private boolean is_refunded;
    private Timestamp created_at;

    public Ticket(Integer id,
                  String code,
                  String title,
                  String place,
                  int user_id,
                  int seance_id,
                  String type,
                  float price,
                  String status,
                  Timestamp used_at,
                  Boolean is_refunded,
                  Timestamp created_at) {
        this.id = id;
        this.code = code;
        this.title = title;
        this.place = place;
        this.user_id = user_id;
        this.seance_id = seance_id;
        this.type = type;
        this.price = price;
        this.status = status;
        this.used_at = used_at;
        this.is_refunded = is_refunded;
        this.created_at = created_at; // Récupère le timestamp dès l'instant où la variable est créée.
    }

    public int getId() {
        return this.id;
    }

    public String getCode() {
        return this.code;
    }

    public String getTitle() {
        return this.title;
    }

    public String getPlace() {
        return this.place;
    }

    public int getUser_id() {
        return this.user_id;
    }

    public int getSeance_id() {
        return this.seance_id;
    }

    public String getType() {
        return this.type;
    }

    public float getPrice() {
        return this.price;
    }

    public String getStatus() {
        return this.status;
    }

    public Timestamp getUsed_at() {
        return this.used_at;
    }

    public boolean getIs_refunded() {
        return this.is_refunded;
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

    public void setCode(String code) {
        if (code != null) {
            this.code = code;
        } else {
            throw new IllegalArgumentException("Le code ne peut pas être null.");
        }
    }

    public void setTitle(String title) {
        if (title != null) {
            this.title = title;
        } else {
            throw new IllegalArgumentException("L'title ne peut pas être null.");
        }
    }

    public void setPlace(String place) {
        if (place != null) {
            this.place = place;
        }
        else {
            throw new IllegalArgumentException("Le mot de passe ne peut pas être null.");
        }
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id; // Peut être null (voir BDD)
    }

    public void setSeance_id(int seance_id) {
        this.seance_id = seance_id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setUsed_at(Timestamp used_at) {
        this.used_at = used_at;
    }

    public void setIs_refunded(boolean is_refunded) {
        this.is_refunded = is_refunded;
    }

    public void setCreated_at(String created_at) {
        this.created_at = new Timestamp(System.currentTimeMillis()); // Peut être null (voir BDD)
    }
}
