package controllers;

import javafx.fxml.FXML; // Librairie JavaFX pour intéragir avec l'interface FXML
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import models.User;

import java.io.IOException;

import static utils.PasswordUtils.checkPassword;


public class LoginController {

    @FXML
    private TextField tfmail;
    @FXML
    private PasswordField tfpassword;
    @FXML
    private Label lbinfo;

    @FXML
    public void btnConnect(ActionEvent event) {
        Stage stage = (Stage) tfmail.getScene().getWindow();

        String mail = tfmail.getText();
        String pass = tfpassword.getText();
//        System.out.println("mail: " + mail); // Test pour vérifier si l'email est correctement récupéré
        User user = dao.UserDAO.getRowByEmail(mail);
        if (user != null) {
            // lbinfo.setText(user.toString());
            if (checkPassword(pass, user.getPassword())) {
                // Ouvrir l'application
                lbinfo.setText("Connexion réussie.");
            } else {
                lbinfo.setText("Mot de passe incorrect");
            }
        } else {
           lbinfo.setText("Email incorrect.");
        }
    }

    @FXML
    private void moveToRegister(ActionEvent mouseEvent) throws IOException {
        Parent registerRoot = FXMLLoader.load(getClass().getResource("/views/RegisterScene.fxml"));
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        Scene registerScene = new Scene(registerRoot);
        stage.setScene(registerScene);
        stage.show();
    }

}
