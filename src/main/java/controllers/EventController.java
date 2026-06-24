package controllers;

import dao.EventDAO;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.Event;
import models.Seance;
import models.User;
import services.SeanceService;
import services.SeanceStatusCache;
import services.SessionManager;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

// Class qui est utilisée lorsque l'utilisateur clique sur un événement
public class EventController implements Initializable {
    private User currentUser;
    private int eventId;
    private Seance currentSeance;
    private HBox currentSeancePane;
    private long seancesLoadVersion;

    @FXML private Label eventTitle;
    @FXML private ImageView eventAffiche;
    @FXML private Label eventDescription;
    @FXML private VBox seancesInfos;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.currentUser = SessionManager.getInstance().getCurrentUser();

        if (currentUser == null) {
            System.out.println("Erreur dans EventController : Aucun utilisateur connecté.");
            AppController.getInstance().loadLogin();
        }
    }

    public void loadSeanceInfos() {
        final int requestedEventId = eventId;
        final long loadVersion = ++seancesLoadVersion;

        Task<List<Seance>> task = new Task<>() {
            @Override
            protected List<Seance> call() {
                List<Seance> seances = EventDAO.getSeances(requestedEventId);
                SeanceService.prepareSeancesForDisplay(seances);
                return seances;
            }
        };

        task.setOnSucceeded(workerStateEvent -> {
            if (loadVersion != seancesLoadVersion || requestedEventId != eventId) {
                return;
            }

            List<Seance> seances = task.getValue();
            List<HBox> cards = buildSeanceCards(seances);

            currentSeance = null;
            currentSeancePane = null;
            seancesInfos.getChildren().setAll(cards);
        });

        task.setOnFailed(workerStateEvent -> {
            if (loadVersion != seancesLoadVersion || requestedEventId != eventId) {
                return;
            }

            currentSeance = null;
            currentSeancePane = null;
            seancesInfos.getChildren().setAll(new Label("Impossible de charger les séances."));

            Throwable exception = task.getException();
            if (exception != null) {
                exception.printStackTrace();
            }
        });

        Thread thread = new Thread(task, "event-seances-loader-" + requestedEventId);
        thread.setDaemon(true);
        thread.start();
    }

    private List<HBox> buildSeanceCards(List<Seance> seances) {
        List<HBox> cards = new ArrayList<>(seances.size());

        for (Seance seance : seances) {
            HBox cardRoot = createSeanceCard(seance);
            if (cardRoot != null) {
                cards.add(cardRoot);
            }
        }

        return cards;
    }

    private HBox createSeanceCard(Seance seance) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/SeanceInfos.fxml"));
            HBox cardRoot = loader.load();

            SeanceInfosController seanceInfosController = loader.getController();
            seanceInfosController.setSeanceInfos(seance);

            cardRoot.setOnMouseClicked(event -> seanceClicked(cardRoot, seance));
            return cardRoot;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setEvent(Event event) {
        this.eventId = event.getId();
        this.currentSeance = null;
        this.currentSeancePane = null;

        setEventTitle(event);
        setEventDescription(event);
        setEventAffiche(event);
        loadSeanceInfos();
    }

    private void setEventId(Event event) {
        this.eventId = event.getId();
    }

    @FXML
    private void setEventTitle(Event event) {
        try {
            String title = event.getName();
            eventTitle.setText(title);

        } catch (Exception e) {
            System.err.println("EventController : erreur dans setEventTitle ; Impossible de récupérer le titre de l'événement " + e.getMessage());
        }
    }

    @FXML
    private void setEventAffiche(Event event) {
        try {
            this.eventId = event.getId();
            String affiche = event.getAffiche();

            if (affiche == null || affiche.isBlank()) {
                eventAffiche.setImage(getDefaultEventImage());
                return;
            }

            Image image = new Image(affiche, true);
            image.errorProperty().addListener((observable, oldValue, isError) -> {
                if (Boolean.TRUE.equals(isError)) {
                    eventAffiche.setImage(getDefaultEventImage());
                }
            });

            if (image.isError()) {
                eventAffiche.setImage(getDefaultEventImage());
                return;
            }

            eventAffiche.setImage(image);

        } catch (Exception e) {
            System.err.println("EventController : erreur dans setEventAffiche ; Impossible de récupérer l'affiche de l'événement. " + e.getMessage());
            eventAffiche.setImage(getDefaultEventImage());
        }
    }

    private Image getDefaultEventImage() {
        return new Image(getClass().getResourceAsStream("/pics/default_event_pic.png"));
    }

    @FXML
    private void setEventDescription(Event event) {
        try {
            this.eventId = event.getId();
            String description = event.getDescription();

            if (description != null && !description.isBlank()) {
                eventDescription.setText(description);
            } else {
                eventDescription.setText("Venez tous à cet événement génial !");
            }

        } catch (Exception e) {
            System.err.println("EventController : erreur dans setEventDescription ; Impossible de récupérer les infos de l'événement. " + e.getMessage());
        }
    }

    @FXML
    private void seanceClicked(HBox cardRoot, Seance seance) {
        if (currentSeancePane != null) {
            currentSeancePane.setStyle("-fx-background-color: #111113; -fx-text-fill: #FFFFFF;");
        }
        currentSeancePane = cardRoot;
        currentSeancePane.setStyle("-fx-background-color: #5B4FFF; -fx-text-fill: #111113;");
        currentSeance = seance;
    }

    @FXML
    public void moveToHome(ActionEvent actionEvent) {
        AppController.getInstance().loadHome();
    }

    @FXML
    public void moveToBuyTicket(ActionEvent actionEvent) {
        if (currentSeance == null) {
            System.out.println("Achat de tickets impossible : aucune séance sélectionnée.");
            return;
        }

        if (SeanceService.isAvailable(currentSeance)) {
            AppController.getInstance().loadSeance(currentSeance);
        } else {
            System.out.println("Achat de tickets impossible : le status de la séance est " + SeanceStatusCache.getInstance().get(currentSeance.getId()));
        }
    }
}
