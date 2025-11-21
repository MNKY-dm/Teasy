package controllers;

import javafx.fxml.FXML; // Librairie JavaFX pour intéragir avec l'interface FXML
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import org.springframework.security.crypto.bcrypt.BCrypt; // Librairie Bcrypt pour les hash de password

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

        lbinfo.setText("Email : " + mail + "\nMot de passe : " + pass);
    }

}
