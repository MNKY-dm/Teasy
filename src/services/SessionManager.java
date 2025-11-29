package services;

import models.User;
import java.time.LocalDateTime;

/**
 * SessionManager : Singleton gérant la session utilisateur
 * <p>
 * Rôle : Savoir QUI est connecté actuellement et POUR COMBIEN DE TEMPS
 * <p>
 * Avantage : Accessible de PARTOUT dans l'app via getInstance()
 * <p>
 * Exemple d'utilisation :
 *   - Dans HomeController : User user = SessionManager.getInstance().getCurrentUser();
 *   - Dans ProfileController : if (SessionManager.getInstance().isAuthenticated()) { ... }
 */
public class SessionManager {

    // Variable statique : une SEULE instance de SessionManager existe pour toute l'app
    private static SessionManager instance;

    // Données de session : l'utilisateur connecté
    private User currentUser;

    // L'heure exacte de la connexion (utile pour tracker)
    private LocalDateTime loginTime;

    // Booléen simple : sommes-nous connectés ?
    private boolean isAuthenticated;

    /**
     * Constructeur PRIVÉ
     * <p>
     * Pourquoi privé ? Pour empêcher qu'on crée plusieurs instances
     * On force l'utilisation de getInstance() à la place
     */
    private SessionManager() {
        this.currentUser = null;
        this.isAuthenticated = false;
        this.loginTime = null;
    }

    /**
     * getInstance() : Pattern Singleton
     * <p>
     * Garantie : Cette méthode retourne TOUJOURS la MÊME instance
     * <p>
     * Premier appel : crée l'instance
     * Appels suivants : retourne la même
     */
    public static SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    /**
     * login(User user) : Enregistrer la connexion
     * <p>
     * Appelé depuis LoginController après vérification email/password réussie
     *
     * @param user : L'utilisateur qui vient de se connecter
     */
    public void login(User user) {
        this.currentUser = user;
        this.loginTime = LocalDateTime.now();
        this.isAuthenticated = true;

        System.out.println("[SESSION] Utilisateur connecté : " + user.getEmail()
                + " (" + user.getRole() + ")");
    }

    /**
     * logout() : Se déconnecter
     * <p>
     * Appelé depuis le bouton "Se déconnecter" n'importe où dans l'app
     * Efface TOUTES les données de session
     */
    public void logout() {
        System.out.println("[SESSION] Utilisateur déconnecté : " +
                (currentUser != null ? currentUser.getEmail() : "none"));

        this.currentUser = null;
        this.isAuthenticated = false;
        this.loginTime = null;
    }

    /**
     * getCurrentUser() : Récupérer l'utilisateur connecté
     *
     * @return L'objet User connecté, ou null si pas connecté
     * <p>
     * Exemple :
     *   User user = SessionManager.getInstance().getCurrentUser();
     *   if (user != null) {
     *       System.out.println("Connecté en tant que : " + user.getEmail());
     *   }
     */
    public User getCurrentUser() {
        return currentUser;
    }

    /**
     * isAuthenticated() : Sommes-nous connectés ?
     *
     * @return true si connecté, false sinon
     * <p>
     * Utile pour :
     *   - Afficher/Cacher des éléments selon l'état
     *   - Rediriger vers login si pas authentifié
     */
    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    /**
     * getLoginTime() : À quelle heure s'est-on connecté ?
     *
     * @return La LocalDateTime de connexion
     *
     * Utile pour : tracker, afficher "Connecté depuis X minutes"
     */
    public LocalDateTime getLoginTime() {
        return loginTime;
    }
}
