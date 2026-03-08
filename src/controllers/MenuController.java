package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import services.SessionManager;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {
    public MenuItem publishEvent;
    @FXML private Menu menuProfile;
    @FXML private Menu menuMyEvents;
    @FXML private Menu menuAdmin;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (SessionManager.getInstance().getCurrentUser() == null) return;

        String role = SessionManager.getInstance().getCurrentUser().getRole();
        switch (role) {
            case "admin":
                menuProfile.setVisible(true);
                menuMyEvents.setVisible(true);
                menuMyEvents.getItems().get(1).setVisible(true);
                menuAdmin.setVisible(true);
                break;
            case "client":
                menuProfile.setVisible(true);
                menuMyEvents.setVisible(true);
                break;
            case "artist":
                menuProfile.setVisible(true);
                menuMyEvents.setVisible(true);
                menuMyEvents.getItems().get(1).setVisible(true);
                break;
        }
    }

    @FXML private void onHome() { AppController.getInstance().loadHome(); }
    @FXML private void onLogout() { AppController.getInstance().loadLogout(); }
}
