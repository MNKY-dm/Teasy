package controllers;

import javafx.fxml.FXML; // Librairie JavaFX pour intéragir avec l'interface FXML
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.User;


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
        models.User user = dao.UserDAO.getRowByEmail(mail);
        if (user != null) {
            // lbinfo.setText(user.toString());
            if (utils.PasswordUtils.checkPassword(pass, user.getPassword())) {
                // Ouvrir l'application
                lbinfo.setText("Connexion réussie.");
            } else {
                lbinfo.setText("Mot de passe incorrect");
            }
        } else {
           lbinfo.setText("Email incorrect.");
        }
    }

}
