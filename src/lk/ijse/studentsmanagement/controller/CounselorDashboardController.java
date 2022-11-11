package lk.ijse.studentsmanagement.controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import lk.ijse.studentsmanagement.util.Navigation;
import lk.ijse.studentsmanagement.util.Routes;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

public class CounselorDashboardController implements Initializable {

    public JFXButton btnLogout;

    public AnchorPane pane;
    public JFXButton btnInquires;

    public JFXButton btnIQTest;
    @FXML
    private AnchorPane mainPane;

    @FXML
    private JFXButton btnViewCourse;

    @FXML
    private JFXButton btnMails;

    @FXML
    private JFXButton btnPayments;

    @FXML
    private JFXButton btnEnrollments;

    @FXML
    private JFXButton btnDashboard;

    @FXML
    private Label lblTime;

    @FXML
    private Label lbldate;

    @FXML
    private Label lbldate2;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        localDateAndTime();
    }
    private void localDateAndTime() {
        lbldate.setText(
                new SimpleDateFormat(" dd : MM : 20yy")
                        .format(
                                new Date()
                        ));
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0),
                event -> lblTime.setText(new SimpleDateFormat("hh:mm:ss  a").format(Calendar.getInstance().getTime()))),
                new KeyFrame(Duration.seconds(1)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
    @FXML
    void btnDashboardOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.COUNSELOR, mainPane);
        System.gc();
    }

    @FXML
    void btnEnrollmentsOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.ENROLLMENTS, pane);
    }

    @FXML
    void btnMailsOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.MAIL_SERVICE_COUNSELOR, pane);
    }

    @FXML
    void btnPaymentsOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.PAYMENTS, pane);
    }

    @FXML
    void btnViewCourseOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.COURSES,pane);
    }

    public void btnInquriesOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.INQUIRIES, pane);
    }

    public void homeOnMouseClicked(MouseEvent mouseEvent) throws IOException {
        Navigation.navigate(Routes.COUNSELOR, mainPane);
    }

    public void btnIQTestOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.IQTest, pane);
    }
    public void btnLogoutOnAction(ActionEvent actionEvent) throws IOException {
        boolean flag = false;
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to logout?");
        alert.setTitle("Log out");
        alert.setHeaderText("Confirmation: ");
        if (alert.showAndWait().get() == ButtonType.OK) {
            Navigation.navigate(Routes.LOGIN, mainPane);
        }
    }
}
