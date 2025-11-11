package controllers;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private TextField tfmail;
    @FXML
    private PasswordField tfpassword;
    @FXML
    private Label lbinfo;

    @FXML
    void btnConnect(ActionEvent event) {
        Stage stage = (Stage) tfmail.getScene().getWindow();

        String mail = tfmail.getText();
        String pass = tfpassword.getText();
        String[] infos = {mail,pass};

        lbinfo.setText("Email : " + infos[0] + "\nMot de passe : " + infos[1]);
    }

}
