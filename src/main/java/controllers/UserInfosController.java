package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import models.User;

public class UserInfosController {
    @FXML private Label userId;
    @FXML private Label userNom;
    @FXML private Label userEmail;
    @FXML private Label userTel;
    @FXML private Label userRole;
    @FXML private Label userCreatedAt;

    public void setUserInfos(User user) {
        try {
            userId.setText(String.valueOf(user.getId()));
            userNom.setText(user.getNom());
            userEmail.setText(user.getEmail());
            userTel.setText(user.getTel() == null || user.getTel().isBlank() ? "-" : user.getTel());
            userRole.setText(user.getRole());
            userCreatedAt.setText(user.getCreated_at() == null
                    ? "-"
                    : user.getCreated_at().toLocalDateTime().toLocalDate().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
