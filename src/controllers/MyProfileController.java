package controllers;

import dao.EventDAO;
import dao.SeanceDAO;
import dao.TicketDAO;
import dao.UserDAO;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import models.Seance;
import models.Ticket;
import models.User;
import services.SessionManager;
import services.TicketService;
import utils.TypeConverter;

import java.net.MalformedURLException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Objects;

public class MyProfileController {
    @FXML public TextField userId;
    @FXML public TextField userName;
    @FXML public TextField userEmail;
    @FXML public TextField userTel;
    @FXML public TextField userRole;
    @FXML public TextField userCreatedAt;

    User user;

    public void setProfile()  {
        User user = SessionManager.getInstance().getCurrentUser();
        this.user = user;

        userId.setText(String.valueOf(user.getId()));
        userName.setText(user.getNom());
        userEmail.setText(user.getEmail());
        userTel.setText(user.getTel());
        userRole.setText(user.getRole());
        System.out.println("MyProfileController : setProfile() : createdAt : " + user.getCreated_at());
        userCreatedAt.setText(TypeConverter.dateFormat(user.getCreated_at()));
    }

    public boolean validateUpdates(Ticket newTicket) {
        if (SeanceDAO.getRowById(newTicket.getSeance_id()) != null && UserDAO.getRowById(newTicket.getUser_id()) != null) {
            // Garder le if si jamais il y a autre chose à ajouter comme vérifications
            return true;
        }
        return false;
    }

    public void saveProfile() {
        System.out.println("saving profile");

        User newProfile;
        try {
            newProfile = new User(
                    userName.getText(),
                    userEmail.getText(),
                    user.getPassword(),
                    userTel.getText(),
                    user.getRole()
            );

        } catch (Exception e) {
            // Si autre erreur détectée, pas de modification
            e.printStackTrace();
            newProfile = user;
            System.out.println("Profil non modifié");
        }

        newProfile.setId(user.getId());
        newProfile.setCreated_at(user.getCreated_at());

        newProfile.update();
        setProfile();

    }

    public void moveToHome() {
        System.out.println("MyProfileController : moveToHome");
        AppController.getInstance().loadHome();
    }

    public void main (String[] args) {
        Ticket test = TicketDAO.getRowById(38);

        System.out.println(validateUpdates(test));
    }

}
