package controllers;

import dao.UserDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.User;
import services.SessionManager;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class UserManagementController implements Initializable {
    private User currentUser;
    private HBox currentUserPane;

    @FXML
    private VBox usersRoot;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("UserManagementController.initialize() appelée");
    }

    public void setUsers() {
        loadUsersInfos();
    }

    public void loadUsersInfos() {
        List<User> users = UserDAO.getAll();
        usersRoot.getChildren().clear();

        for (User user : users) {
            addUserInfos(user);
        }
    }

    @FXML
    public void addUserInfos(User user) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/UserInfos.fxml"));
            HBox cardRoot = loader.load();

            UserInfosController userInfosController = loader.getController();
            userInfosController.setUserInfos(user);

            cardRoot.setOnMouseClicked(event -> userClicked(cardRoot, user));
            usersRoot.getChildren().add(cardRoot);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void userClicked(HBox cardRoot, User user) {
        if (currentUserPane != null) {
            currentUserPane.setStyle("-fx-background-color: #111113; -fx-text-fill: #FFFFFF;");
        }

        currentUserPane = cardRoot;
        currentUserPane.setStyle("-fx-background-color: #5B4FFF; -fx-text-fill: #111113;");
        currentUser = user;
    }

    @FXML
    private void updateUserInfos() {
        if (currentUser == null) {
            System.out.println("Aucun utilisateur sélectionné");
            return;
        }

        AppController.getInstance().loadEditUser(currentUser);
    }

    @FXML
    private void deleteUser() {
        if (currentUser == null) {
            System.out.println("Aucun utilisateur sélectionné");
            return;
        }

        User sessionUser = SessionManager.getInstance().getCurrentUser();
        if (sessionUser != null && sessionUser.getId() != null && sessionUser.getId().equals(currentUser.getId())) {
            System.out.println("Impossible de supprimer l'utilisateur connecté");
            return;
        }

        boolean deleted = UserDAO.deleteRowById(currentUser.getId());
        if (deleted) {
            usersRoot.getChildren().remove(currentUserPane);
            currentUserPane = null;
            currentUser = null;
        }
    }

    @FXML
    public void moveToHome(ActionEvent actionEvent) {
        AppController.getInstance().loadHome();
    }
}
