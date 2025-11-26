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
    private TextField tfnom;
    @FXML
    private TextField tfmail;
    @FXML
    private TextField tftel;
    @FXML
    private TextField tfrole;
    @FXML
    private PasswordField tfpassword;
    @FXML
    private PasswordField tfpasswordConfirm;
    @FXML
    private Label lbinfo;
    @FXML
    private Label lbconfirm;

    @FXML
    public void btnRegister(ActionEvent event) {

        Stage stage = (Stage) tfmail.getScene().getWindow();

        String nom = tfnom.getText();
        String mail = tfmail.getText();
        String tel = tftel.getText();
        String role = tfrole.getText();
        String pass = tfpassword.getText();
        String confirmedPass = tfpasswordConfirm.getText();
        User user = dao.UserDAO.getRowByEmail(mail);
        if (user == null) {
        // lbinfo.setText(user.toString());
            if (Objects.equals(pass, confirmedPass)) {
                lbinfo.setText("Inscription réussie.");
//                user = new User(id, nom, mail, utils.PasswordUtils.hashPassword(pass), tel, role, created_at);
                dao.UserDAO.insertNewRow(user);
            } else {
                lbconfirm.setText("Les mots de passe ne correspondent pas.");
            }
        } else {
            lbinfo.setText("Un compte existe déjà avec cette adresse mail. Vous pouvez vous y connecter.");
        }
    }


    @FXML
    private void moveToConnect(ActionEvent mouseEvent) throws IOException {
        Parent registerRoot = FXMLLoader.load(getClass().getResource("/views/ConnectScene.fxml"));
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        Scene registerScene = new Scene(registerRoot);
        stage.setScene(registerScene);
        stage.show();
    }
}
