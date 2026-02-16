package services;

import models.User;
import java.time.LocalDateTime;

public class SessionManager {
    private static SessionManager instance;
    private User currentUser;
    private boolean isAuthenticated;

    private SessionManager() {
        this.currentUser = null;
        this.isAuthenticated = false;
    }

    public static SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    public void login(User user) {
        this.currentUser = user;
        this.isAuthenticated = true;

        System.out.println("[SESSION] Utilisateur connecté : " + user.getEmail()
                + " (" + user.getRole() + ")");
    }

    public void logout() {
        System.out.println("[SESSION] Utilisateur déconnecté : " +
                (currentUser != null ? currentUser.getEmail() : "none"));

        this.currentUser = null;
        this.isAuthenticated = false;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public boolean isAuthenticated() {
        return isAuthenticated;
    }
}
