package controllers;

import dao.UserDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import models.User;
import services.SessionManager;
import utils.PasswordUtils;

/**
 * LoginController : Gère la page de connexion
 * <p>
 * AVANT (ancien code) :
 *   - Vérifier email/password
 *   - Charger RegisterScene directement
 * <p>
 * APRÈS (nouveau code) :
 *   - Vérifier email/password (MÊME CODE)
 *   - Sauvegarder user dans SessionManager (NOUVEAU)
 *   - Appeler AppController.loadHome() (NOUVEAU)
 * <p>
 * Avantage : LoginController ne crée plus ses propres scenes
 *            AppController gère toute la navigation
 */
public class LoginController {

    // Composants FXML
    @FXML
    private TextField tfmail;

    @FXML
    private PasswordField tfpassword;

    @FXML
    private Label lbinfo;

    // DAO pour accéder à la base de données
    private UserDAO userDAO;

    /**
     * Constructeur
     * <p>
     * Initialiser le DAO
     */
    public LoginController() {
        this.userDAO = new UserDAO();
    }

    /**
     * btnConnect() : Bouton "Se connecter"
     * <p>
     * Processus :
     *   1. Récupérer email et password depuis les champs
     *   2. Chercher l'utilisateur en base de données
     *   3. SI trouvé ET password correct :
     *      - Sauvegarder dans SessionManager
     *      - Charger la page HOME
     *   4. SINON : Afficher message d'erreur
     */
    @FXML
    public void btnConnect() {
        // Récupérer les données du formulaire
        String email = tfmail.getText();
        String password = tfpassword.getText();

        // vérifier si les champs sont bien remplis
        if (email.isEmpty() || password.isEmpty()) {
            lbinfo.setText("Veuillez remplir tous les champs");
            return;
        }

        // Chercher l'utilisateur en base de données
        User user = UserDAO.getRowByEmail(email);

        // Vérifier si l'utilisateur existe et si le mot de passe est correct
        if (user != null && PasswordUtils.checkPassword(password, user.getPassword())) {

            // CONNEXION RÉUSSIE

            // Sauvegarder l'utilisateur dans la SESSION
            SessionManager.getInstance().login(user);

            // Charger la page HOME via AppController
            AppController.getInstance().loadHome();

            // Feedback utilisateur
            lbinfo.setText("Connexion réussie !");

        } else {

            // CONNEXION ÉCHOUÉE
            lbinfo.setText("Email ou mot de passe incorrect");
            tfpassword.clear();  // Vider le champ password pour recommencer facilement
        }
    }

    /**
     * moveToRegister() : Bouton "S'inscrire"
     * <p>
     * Appelé depuis le bouton "S'inscrire" du formulaire de login
     * <p>
     * AVANT : Chargeait RegisterScene directement
     * APRÈS : Appelle AppController pour charger Register
     * <p>
     * Avantage : Logique de navigation CENTRALISÉE
     */
    @FXML
    public void moveToRegister() {
        // Utiliser AppController pour la navigation
        AppController.getInstance().loadRegister();
    }
}
