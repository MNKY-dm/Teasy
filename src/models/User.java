package models;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class User {
    private Integer id;
    private String nom;
    private String email;
    private String password;
    private String tel;
    private String role;
    private Timestamp created_at;

    public User(Integer id, String nom, String email, String password, String tel, String role, Timestamp created_at) {
        this.id = id;
        this.nom = nom;
        this.email = email;
        this.password = password;
        this.tel = tel;
        this.role = role;
        this.created_at = created_at; // Récupère le timestamp dès l'instant où la variable est créée.
    }

    public int getId() {
        return this.id;
    }

    public String getNom() {
        return this.nom;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public String getTel() {
        return this.tel;
    }

    public String getRole() {
        return this.role;
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

    public void setNom(String nom) {
        if (nom != null) {
            this.nom = nom;
        } else {
            throw new IllegalArgumentException("Le nom ne peut pas être null.");
        }
    }

    public void setEmail(String email) {
        if (email != null) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("L'email ne peut pas être null.");
        }
    }

    public void setPassword(String password) {
        if (password != null) {
            this.password = password;
        }
        else {
            throw new IllegalArgumentException("Le mot de passe ne peut pas être null.");
        }
    }

    public void setTel(String tel) {
        this.tel = tel; // Peut être null (voir BDD)
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setCreated_at(String created_at) {
        this.created_at = new Timestamp(System.currentTimeMillis()); // Peut être null (voir BDD)
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", email='" + email + '\'' +
                ", mdp='" + password + '\'' +
                ", tel='" + tel + '\'' +
                ", role='" + role + '\'' +
                ", créé le='" + created_at + '\'' +
                '}';
    }

}
