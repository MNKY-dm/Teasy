package controllers;

import dao.EventDAO;
import dao.SeanceDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.Event;
import models.Seance;
import models.Ticket;
import services.SessionManager;

import java.io.IOException;


// AppController : gérer les changements de vues, injecter les controllers

public class AppController {

    // Variables statiques : une SEULE instance d'AppController
    private static AppController instance;

    // Stage : la fenêtre principale de l'app
    private Stage primaryStage;

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
    private void loadScene(String fxmlFile) throws IOException {
        System.out.println("[APPCONTROLLER] Chargement de : " + fxmlFile);

        // Charger le fichier FXML
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/" + fxmlFile)
        );

        // Charger le contenu FXML dans un Parent (conteneur)
        Parent root = loader.load();

        // Scene courante : le contenu actuellement affiché
        Scene currentScene = new Scene(root);
        currentScene.getStylesheets().add(
                getClass().getResource("/styles/style.css").toExternalForm()
        );

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
            primaryStage.setMinWidth(1200);
            primaryStage.setMinHeight(900);

        } catch (IOException e) {
            System.err.println("[ERREUR] Impossible de charger HomeScene.fxml");
            e.printStackTrace();
        }
    }

    @FXML
    public void loadEvent(Event event) {
        try {
            System.out.println("[APPCONTROLLER] : loadEvent --> Chargement de : " + event);
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
            primaryStage.setResizable(true);

            Scene scene = new Scene(root);
            scene.getStylesheets().add(
                    getClass().getResource("/styles/style.css").toExternalForm()
            );
            primaryStage.setScene(scene);
        } catch (IOException e) {
            System.err.println("[ERREUR] Impossible de charger EventScene.fxml");
            e.printStackTrace();
        }
    }

    public void loadSeance(Seance seance) {
        try {
            System.out.println("[APPCONTROLLER] : loadSeance --> Chargement de : " + seance);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/SeanceScene.fxml"));
            Parent root = loader.load();

            SeanceController seanceController = loader.getController();
            Event event = EventDAO.getRowById(seance.getEvent_id());
            seanceController.setSeance(seance, event);

            String eventName = event.getName();
            String title = "Teasy - Séance (" + eventName + ") - " + seance.getDate();
            primaryStage.setTitle(title);
            primaryStage.setMinWidth(600);
            primaryStage.setMinHeight(400);

            Scene scene = new Scene(root);
            scene.getStylesheets().add(
                    getClass().getResource("/styles/style.css").toExternalForm()
            );
            primaryStage.setScene(scene);
        } catch (IOException e) {
            System.err.println("[ERREUR] Impossible de charger SeanceScene.fxml");
            e.printStackTrace();
        }
    }

// ------------------ Options du menu ------------------ //

    public void loadMyTickets() {
        try {
            System.out.println("[APPCONTROLLER] : loadMyTickets");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/MyTicketsScene.fxml"));
            Parent root = loader.load();

            // Récupérer le contrôleur créé par FXMLLoader (celui qui a les @FXML injectés)
            MyTicketsController myTicketsController = loader.getController();

            myTicketsController.setTickets();

            primaryStage.setTitle("Teasy - Mes tickets");
            primaryStage.setMinWidth(600);
            primaryStage.setMinHeight(400);

            Scene scene = new Scene(root);
            scene.getStylesheets().add(
                    getClass().getResource("/styles/style.css").toExternalForm()
            );
            primaryStage.setScene(scene);
        } catch (IOException e) {
            System.err.println("[ERREUR] Impossible de charger MyTicketsScene.fxml");
            e.printStackTrace();
        }
    }

    public void loadMyEvents() {
        try {
            System.out.println("[APPCONTROLLER] : loadMyEvents");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/MyEventsScene.fxml"));
            Parent root = loader.load();

            // Récupérer le contrôleur créé par FXMLLoader (celui qui a les @FXML injectés)
            MyEventsController myeventsController = loader.getController();

            myeventsController.setEvents();

            primaryStage.setTitle("Teasy - Mes événements");
            primaryStage.setMinWidth(600);
            primaryStage.setMinHeight(400);

            Scene scene = new Scene(root);
            scene.getStylesheets().add(
                    getClass().getResource("/styles/style.css").toExternalForm()
            );
            primaryStage.setScene(scene);
        } catch (IOException e) {
            System.err.println("[ERREUR] Impossible de charger MyEventsScene.fxml");
            e.printStackTrace();
        }
    }

    public void loadPublishEvent() {
        try {
            System.out.println("[APPCONTROLLER] : loadPublishEvent");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/PublishEventScene.fxml"));
            Parent root = loader.load();

            PublishEventController publishEventController = loader.getController();

            primaryStage.setTitle("Teasy - Publier un nouvel événement");
            primaryStage.setMinWidth(600);
            primaryStage.setMinHeight(400);

            Scene scene = new Scene(root);
            scene.getStylesheets().add(
                getClass().getResource("/styles/style.css").toExternalForm()
            );
            primaryStage.setScene(scene);
        }  catch (IOException e) {
            System.err.println("[ERREUR] Impossible de charger PublishEventScene.fxml");
            e.printStackTrace();
        }
    }

    public void loadMyProfile() {
        try {
            System.out.println("[APPCONTROLLER] : loadMyProfile");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/MyProfileScene.fxml"));
            Parent root = loader.load();
            MyProfileController myProfileController = loader.getController();

            myProfileController.setProfile();

            primaryStage.setTitle("Teasy - Mon profil");
            primaryStage.setMinWidth(600);
            primaryStage.setMinHeight(400);

            Scene scene = new Scene(root);
            scene.getStylesheets().add(
                getClass().getResource("/styles/style.css").toExternalForm()
            );
            primaryStage.setScene(scene);
        } catch (IOException e) {
            System.err.println("[ERREUR] Impossible de charger MyProfileScene.fxml");
            e.printStackTrace();
        }
    }

    public void loadAdminPanel() {
        try {
            System.out.println("[APPCONTROLLER] : loadAdminPanel");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/AdminPanelScene.fxml"));
            Parent root = loader.load();

            // Récupérer le contrôleur créé par FXMLLoader (celui qui a les @FXML injectés)
            AdminPanelController adminPanelController = loader.getController();

            primaryStage.setTitle("Teasy - Panel Admin");
            primaryStage.setMinWidth(600);
            primaryStage.setMinHeight(400);

            Scene scene = new Scene(root);
            scene.getStylesheets().add(
                    getClass().getResource("/styles/style.css").toExternalForm()
            );
            primaryStage.setScene(scene);
        } catch (IOException e) {
            System.err.println("[ERREUR] Impossible de charger AdminPanelScene.fxml");
            e.printStackTrace();
        }
    }

    // Fonctionnalités relatives au panel admin
    public void loadTicketsManagement() {
        try {
            System.out.println("[APPCONTROLLER] : loadTicketsManagement");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/TicketsManagementScene.fxml"));
            Parent root = loader.load();

            // Récupérer le contrôleur créé par FXMLLoader (celui qui a les @FXML injectés)
            TicketsManagementController ticketsManagementController = loader.getController();

            ticketsManagementController.setTickets();

            primaryStage.setTitle("Teasy - Panel Admin");
            primaryStage.setMinWidth(600);
            primaryStage.setMinHeight(400);

            Scene scene = new Scene(root);
            scene.getStylesheets().add(
                    getClass().getResource("/styles/style.css").toExternalForm()
            );
            primaryStage.setScene(scene);
        } catch (IOException e) {
            System.err.println("[ERREUR] Impossible de charger TicketsManagementScene.fxml");
            e.printStackTrace();
        }
    }

    public void loadTicket(Ticket ticket) {
        try {
            System.out.println("[APPCONTROLLER] : loadTicket --> Chargement de : " + ticket);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/TicketScene.fxml"));
            Parent root = loader.load();

            TicketController ticketController = loader.getController();
            ticketController.setTicket(ticket);

            String eventName = ticket.getTitle();
            String title = "Teasy - Ticket "  + ticket.getType() + " (" + eventName + ")";
            primaryStage.setTitle(title);
            primaryStage.setMinWidth(600);
            primaryStage.setMinHeight(400);

            Scene scene = new Scene(root);
            scene.getStylesheets().add(
                    getClass().getResource("/styles/style.css").toExternalForm()
            );
            primaryStage.setScene(scene);
        } catch (IOException e) {
            System.err.println("[ERREUR] Impossible de charger TicketScene.fxml");
            e.printStackTrace();
        }
    }

    public void loadTicketModifier(Ticket ticket) {
        try {
            System.out.println("[APPCONTROLLER] : loadTicketModifier --> Chargement de : " + ticket);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/TicketModifierScene.fxml"));
            Parent root = loader.load();

            TicketModifierController ticketModifierController = loader.getController();
            ticketModifierController.setTicket(ticket);

            String eventName = ticket.getTitle();
            String title = "Teasy - Ticket "  + ticket.getType() + " (" + eventName + ")";
            primaryStage.setTitle(title);
            primaryStage.setMinWidth(600);
            primaryStage.setMinHeight(400);

            Scene scene = new Scene(root);
            scene.getStylesheets().add(
                    getClass().getResource("/styles/style.css").toExternalForm()
            );
            primaryStage.setScene(scene);
        } catch (IOException e) {
            System.err.println("[ERREUR] Impossible de charger TicketModifierScene.fxml");
            e.printStackTrace();
        }
    }

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
