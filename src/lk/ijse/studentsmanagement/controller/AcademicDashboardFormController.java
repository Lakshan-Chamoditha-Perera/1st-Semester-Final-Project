package lk.ijse.studentsmanagement.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.studentsmanagement.util.Navigation;
import lk.ijse.studentsmanagement.util.Routes;

import java.io.IOException;

public class AcademicDashboardFormController {

    public ImageView btnHome;
    @FXML
    private AnchorPane mainPane;

    @FXML
    private JFXButton btnMails;

    @FXML
    private JFXButton btnAttendance;

    @FXML
    private JFXButton btnStudents;

    @FXML
    private JFXButton btnDashboard;

    @FXML
    private JFXButton btnLogout;

    @FXML
    private JFXButton btnInquires;

    @FXML
    private JFXButton btnExam;

    @FXML
    private Label lbldate;

    @FXML
    private Label lbldate2;

    @FXML
    private Label lblTime;

    @FXML
    private AnchorPane pane;

    @FXML
    void btnAttendanceOnAction(ActionEvent event) {

    }

    @FXML
    void btnDashboardOnAction(ActionEvent event) {

    }

    @FXML
    void btnExamOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.EXAMS,pane);
    }

    @FXML
    void btnInquriesOnAction(ActionEvent event) {

    }

    @FXML
    void btnLogoutOnAction(ActionEvent event) throws IOException {
        boolean flag = false;
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to logout?");
        alert.setTitle("Log out");
        alert.setHeaderText("Confirmation: ");
        if (alert.showAndWait().get() == ButtonType.OK) {
            Navigation.navigate(Routes.LOGIN, mainPane);
        }
    }

    @FXML
    void btnMailsOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.MAIL_SERVICE_COUNSELOR, pane);
    }

    @FXML
    void btnStudentsOnAction(ActionEvent event) {

    }

    @FXML
    void homeOnMouseClicked(MouseEvent event) throws IOException {
        Navigation.navigate(Routes.ACADEMIC,mainPane);
    }

}
