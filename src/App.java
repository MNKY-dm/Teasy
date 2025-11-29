import controllers.AppController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            // Initialiser AppController
            AppController.getInstance().init(primaryStage);

            // Charger la première page (Login)
            AppController.getInstance().loadLogin();

            // Nommer la fenêtre
            primaryStage.setTitle("Teasy - Billeterie");

            // Afficher la fenêtre
            primaryStage.show();

            System.out.println("[APP] Application démarrée avec succès");

        } catch (Exception e) {
            System.err.println("[ERREUR] Erreur au démarrage de l'application");
            e.printStackTrace();
        }
    }

    /**
     * main(String[] args) : Point d'entrée Java
     *
     * Appelé d'abord, puis lance Application.launch()
     * qui appelle start()
     */
    public static void main(String[] args) {
        launch(args);
    }
}