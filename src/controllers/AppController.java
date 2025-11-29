package controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import services.SessionManager;

import java.io.IOException;

/**
 * AppController : Gestionnaire CENTRAL de navigation
 * <p>
 * Rôle : Gérer les changements de scene
 * <p>
 * Analogie : AppController = le "directeur de scène"
 *   - Il sait quelle scène afficher
 *   - Il charge les fichiers FXML
 *   - Il injecte les controllers
 * <p>
 * Usage :
 *   - AppController.getInstance().loadHome()
 *   - AppController.getInstance().loadProfile()
 *   - AppController.getInstance().loadEvents()
 */
public class AppController {

    // Variables statiques : une SEULE instance d'AppController
    private static AppController instance;

    // Stage : la fenêtre principale de l'app
    private Stage primaryStage;

    // Scene courante : le contenu actuellement affiché
    private Scene currentScene;

    /**
     * Constructeur PRIVÉ (Singleton)
     */
    private AppController() {}

    /**
     * getInstance() : Singleton pattern
     */
    public static AppController getInstance() {
        if (instance == null) {
            instance = new AppController();
        }
        return instance;
    }

    /**
     * init(Stage primaryStage) : Initialiser AppController
     * <p>
     * Appelé UNE FOIS depuis App.java au démarrage
     *
     * @param primaryStage : La fenêtre principale de JavaFX
     */
    public void init(Stage primaryStage) {
        this.primaryStage = primaryStage;
        System.out.println("[APPCONTROLLER] Initialisé");
    }

    /**
     * loadScene(String fxmlFile) : Charger n'importe quelle scène
     * <p>
     * Méthode INTERNE utilisée par les autres loadXxx()
     * <p>
     * Processus :
     *   1. Charge le fichier FXML
     *   2. Récupère le controller associé
     *   3. Remplace la scene courante
     *   4. Met à jour l'affichage
     *
     * @param fxmlFile : Chemin du fichier FXML (ex: "views/HomeScene.fxml")
     * @throws IOException : Si le fichier n'existe pas
     */
    private void loadScene(String fxmlFile) throws IOException {
        System.out.println("[APPCONTROLLER] Chargement de : " + fxmlFile);

        // Charger le fichier FXML
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/" + fxmlFile)
        );

        // Charger le contenu FXML dans un Parent (conteneur)
        Parent root = loader.load();

        // Créer une nouvelle Scene avec ce contenu
        currentScene = new Scene(root);

        // Afficher cette scène dans la Stage
        primaryStage.setScene(currentScene);
    }

    /**
     * loadLogin() : Charger la page de LOGIN
     * <p>
     * Appelé au premier démarrage ou lorsque l'user s'est déconnecté
     */
    public void loadLogin() {
        try {
            loadScene("views/ConnectScene.fxml");
            primaryStage.setTitle("Teasy - Connexion");
        } catch (IOException e) {
            System.err.println("[ERREUR] Impossible de charger ConnectScene.fxml");
            e.printStackTrace();
        }
    }

    /**
     * loadRegister() : Charger la page d'INSCRIPTION
     * <p>
     * Appelé depuis le bouton "S'inscrire" de ConnectScene
     */
    public void loadRegister() {
        try {
            loadScene("views/RegisterScene.fxml");
            primaryStage.setTitle("Teasy - Inscription");
        } catch (IOException e) {
            System.err.println("[ERREUR] Impossible de charger RegisterScene.fxml");
            e.printStackTrace();
        }
    }

    /**
     * loadHome() : Charger la page d'ACCUEIL
     * <p>
     * Appelé après connexion réussie
     * IMPORTANT : L'utilisateur doit être dans SessionManager avant !
     */
    public void loadHome() {
        try {
            loadScene("views/HomeScene.fxml");

            // Adapter le titre en fonction du rôle
            String role = SessionManager.getInstance().getCurrentUser().getRole();
            String title = "Teasy - Accueil (" + role + ")";
            primaryStage.setTitle(title);

        } catch (IOException e) {
            System.err.println("[ERREUR] Impossible de charger HomeScene.fxml");
            e.printStackTrace();
        }
    }

    // =====================================================
    // TU PEUX AJOUTER D'AUTRES MÉTHODES ICI POUR CHAQUE PAGE
    // =====================================================

    /**
     * loadEvents() : Charger la page ÉVÉNEMENTS
     * <p>
     * À décommenter et utiliser quand tu auras CreatedHomeScene.fxml
     */
    /*
    public void loadEvents() {
        try {
            loadScene("views/EventsScene.fxml");
            primaryStage.setTitle("Teasy - Événements");
        } catch (IOException e) {
            System.err.println("[ERREUR] Impossible de charger EventsScene.fxml");
            e.printStackTrace();
        }
    }
    */

    /**
     * loadProfile() : Charger la page PROFIL
     * <p>
     * À ajouter quand tu auras ProfileScene.fxml
     */
    /*
    public void loadProfile() {
        try {
            loadScene("views/ProfileScene.fxml");
            primaryStage.setTitle("Teasy - Profil");
        } catch (IOException e) {
            System.err.println("[ERREUR] Impossible de charger ProfileScene.fxml");
            e.printStackTrace();
        }
    }
    */

    /**
     * loadLogout() : Charger la page de LOGIN après déconnexion
     * <p>
     * Appelé depuis le bouton "Se déconnecter"
     */
    public void loadLogout() {
        SessionManager.getInstance().logout();
        loadLogin();
    }

    /**
     * getPrimaryStage() : Obtenir la Stage principale
     * <p>
     * Utile si besoin d'accéder à des propriétés (taille, position, etc.)
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }
}
