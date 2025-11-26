package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
import java.util.Objects;

public class RegisterController {
    @FXML
    private TextField tfmail;
    @FXML
    private PasswordField tfpassword;
    @FXML
    private PasswordField tfpasswordConfirm;
    @FXML
    private Label lbinfo;
    @FXML
    private Label lbconfirm;

    public void btnRegister(ActionEvent event) {

        Stage stage = (Stage) tfmail.getScene().getWindow();

        String mail = tfmail.getText();
        String pass = tfpassword.getText();
        String confirmedPass = tfpasswordConfirm.getText();
        User user = dao.UserDAO.getRowByEmail(mail);
        if (user == null) {
        // lbinfo.setText(user.toString());
            if (Objects.equals(pass, confirmedPass)) {
                lbinfo.setText("Inscription réussie.");
            } else {
                lbconfirm.setText("Les mots de passe ne correspondent pas.");
            }
        } else {
            lbinfo.setText("Un compte existe déjà avec cette adresse mail. Vous pouvez vous y connecter.");
        }
    }


    @FXML
    private void moveToConnect(ActionEvent mouseEvent) throws IOException {
        Parent registerRoot = FXMLLoader.load(getClass().getResource("RegisterScene.fxml"));
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        Scene registerScene = new Scene(registerRoot);
        stage.setScene(registerScene);
        stage.show();
    }
}
