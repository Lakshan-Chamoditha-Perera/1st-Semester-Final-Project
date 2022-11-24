package lk.ijse.studentsmanagement.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.studentsmanagement.util.Navigation;
import lk.ijse.studentsmanagement.util.Routes;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static lk.ijse.studentsmanagement.util.TimeDate.localDateAndTime;

public class AcademicDashboardFormController implements Initializable {

    public ImageView btnHome;
    public Label lblDate;
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
    private Label lblTime;

    @FXML
    private AnchorPane pane;

    @FXML
    void btnAttendanceOnAction(ActionEvent event) {

    }

    @FXML
    void btnDashboardOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.ACADEMIC, mainPane);
    }

    @FXML
    void btnExamOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.EXAMS, pane);
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
    void btnStudentsOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.ACADEMIC_STUDENTFORM, pane);

    }

    @FXML
    void homeOnMouseClicked(MouseEvent event) throws IOException {
        Navigation.navigate(Routes.ACADEMIC, mainPane);
    }

    public void btnManageBatchesOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ACADEMIC_MANAGE_BATCHES, pane);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        localDateAndTime(lblDate, lblTime);
    }

    public void btnManageSubjectOnAction(ActionEvent actionEvent) throws IOException {
         Navigation.navigate(Routes.ACADEMIC_MANAGE_SUBJECTS,pane);
    }
}
