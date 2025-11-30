package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.Event;
import services.SessionManager;

import java.io.IOException;


// AppController : gérer les changements de vues, injecter les controllers

public class AppController {

    // Variables statiques : une SEULE instance d'AppController
    private static AppController instance;

    // Stage : la fenêtre principale de l'app
    private Stage primaryStage;

    // Scene courante : le contenu actuellement affiché
    private Scene currentScene;

    private AppController() {}

    // Pattern SingleTon
    public static AppController getInstance() {
        if (instance == null) {
            instance = new AppController();
        }
        return instance;
    }


    //init(Stage primaryStage) : Initialiser AppController → utilisé seulement une fois au lancement de App.java

    public void init(Stage primaryStage) {
        this.primaryStage = primaryStage;
        System.out.println("[APPCONTROLLER] Initialisé");
    }


//  loadScene(String fxmlFile) : Charger n'importe quelle scène
//  Méthode interne utilisée par les autres loadXxx()
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

        // Afficher cette scène dans le Stage
        primaryStage.setScene(currentScene);
    }


    // loadLogin() : Charger la page ConnectScene.fxml (Appelé au premier démarrage ou lorsque l'user s'est déconnecté)
    public void loadLogin() {
        try {
            loadScene("views/ConnectScene.fxml");
            primaryStage.setTitle("Teasy - Connexion");
            primaryStage.setResizable(false);
        } catch (IOException e) {
            System.err.println("[ERREUR] Impossible de charger ConnectScene.fxml");
            e.printStackTrace();
        }
    }

    // loadRegister() : Charger la page RegisterScene.fxml (Appelé depuis le bouton "S'inscrire" de ConnectScene)
    public void loadRegister() {
        try {
            loadScene("views/RegisterScene.fxml");
            primaryStage.setTitle("Teasy - Inscription");
            primaryStage.setResizable(false);
        } catch (IOException e) {
            System.err.println("[ERREUR] Impossible de charger RegisterScene.fxml");
            e.printStackTrace();
        }
    }


    // loadHome() : Charger la page HomeScene.fxml
    public void loadHome() {
        try {
            loadScene("views/HomeScene.fxml");

            // Adapter le titre en fonction du rôle
            String role = SessionManager.getInstance().getCurrentUser().getRole();
            String title = "Teasy - Billetterie - Accueil (" + role + ")";
            primaryStage.setTitle(title);
            primaryStage.setMinWidth(600);
            primaryStage.setMinHeight(400);

        } catch (IOException e) {
            System.err.println("[ERREUR] Impossible de charger HomeScene.fxml");
            e.printStackTrace();
        }
    }

    @FXML
    public void loadEvent(Event event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/EventScene.fxml"));
            Parent root = loader.load();

            // Récupérer le contrôleur créé par FXMLLoader (celui qui a les @FXML injectés)
            EventController eventController = loader.getController();  // ← LE BON

            // Maintenant appeler setEvent sur le bon contrôleur (celui avec @FXML)
            eventController.setEvent(event);

            // Adapter le titre, etc.
            String eventName = event.getName();
            String title = "Teasy - Événement (" + eventName + ")";
            primaryStage.setTitle(title);
            primaryStage.setMinWidth(600);
            primaryStage.setMinHeight(400);

            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
        } catch (IOException e) {
            System.err.println("[ERREUR] Impossible de charger EventScene.fxml");
            e.printStackTrace();
        }
    }
    public void loadBuyTicket(Event event) {
        try {
            loadScene("views/BuyTicketScene.fxml");

            // Adapter le titre en fonction de l'event cliqué
            String eventName = event.getName();
            String title = "Teasy - Acheter un ticket (" + eventName + ")";
            primaryStage.setTitle(title);
            primaryStage.setMinWidth(600);
            primaryStage.setMinHeight(400);
        } catch (IOException e) {
            System.err.println("[ERREUR] Impossible de charger BuyTicketScene.fxml");
            e.printStackTrace();
        }
    }

    // =====================================================
    // AJOUTER D'AUTRES loadPage() POUR CHAQUE PAGE
    // =====================================================


    //  loadEvents() : Charger la page ÉVÉNEMENTS (atteignable depuis l'accueil)
//    public void loadEvents() {
//        try {
//            loadScene("views/EventsScene.fxml");
//            primaryStage.setTitle("Teasy - Événements");
//        } catch (IOException e) {
//            System.err.println("[ERREUR] Impossible de charger EventsScene.fxml");
//            e.printStackTrace();
//        }
//    }


//     * loadProfile() : Charger la page PROFIL (lorsque l'user clique sur "Profil")
//    public void loadProfile() {
//        try {
//            loadScene("views/ProfileScene.fxml");
//            primaryStage.setTitle("Teasy - Profil");
//        } catch (IOException e) {
//            System.err.println("[ERREUR] Impossible de charger ProfileScene.fxml");
//            e.printStackTrace();
//        }
//    }


    // loadLogout() : Charger la page ConnectScene.fxml après déconnexion (depuis Profil → Se déconnecter)

    public void loadLogout() {
        SessionManager.getInstance().logout();
        System.out.println("En cours de déconnexion");
        loadLogin();
    }


    // getPrimaryStage() : Obtenir la Stage principale si jamais besoin d'obtenir les propriétés de la scene courante
    public Stage getPrimaryStage() {
        return primaryStage;
    }
}
