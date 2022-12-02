package lk.ijse.studentsmanagement.controller;

import animatefx.animation.*;
import com.jfoenix.controls.JFXButton;
import com.sun.scenario.animation.AnimationPulse;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.studentsmanagement.comboLoad.TableLoader;
import lk.ijse.studentsmanagement.tblModels.ExamTM;
import lk.ijse.studentsmanagement.util.Navigation;
import lk.ijse.studentsmanagement.util.Routes;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static lk.ijse.studentsmanagement.util.TimeDate.localDateAndTime;
import static lk.ijse.studentsmanagement.util.TimeDate.setGreeting;

public class AcademicDashboardFormController implements Initializable {

    public ImageView btnHome;
    public Label lblDate;
    public ImageView wishImageView;
    public Label lblGreetings;
    public Label lbldate2;
    public AnchorPane pane2;
    public Label lblWelcome;
    public Label lblAnythingDo;
    public JFXButton btnBatches;
    public JFXButton btnSubjects;
    public JFXButton btnPayments;
    @FXML
    private AnchorPane mainPane;
    @FXML
    private TableView<ExamTM> tblExams;

    @FXML
    private TableColumn<?, ?> colSubjectID;

    @FXML
    private TableColumn<?, ?> colBatchId;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colTime;

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
    void btnAttendanceOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.MARK_ATTENDANCE, pane);
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
        setGreeting(lblGreetings,wishImageView);
        new FadeIn(pane2).play();
        new FadeInRightBig(lblAnythingDo).play();
        new FadeInRightBig(lblWelcome).play();

        new FadeInLeftBig(btnDashboard).play();
        new FadeInLeftBig(btnStudents).play();
        new FadeInLeftBig(btnExam).play();
        new FadeInLeftBig(btnAttendance).play();
        new FadeInLeftBig(btnBatches).play();
        new FadeInLeftBig(btnSubjects).play();
        new FadeInLeftBig(btnPayments).play();

        new FadeInLeftBig(btnMails).play();
       // colExamID.setCellValueFactory(new PropertyValueFactory<>("examId"));
        colBatchId.setCellValueFactory(new PropertyValueFactory<>("batchId"));
    //    colLab.setCellValueFactory(new PropertyValueFactory<>("lab"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("examDate"));
        colSubjectID.setCellValueFactory(new PropertyValueFactory<>("subjectId"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));

        try {
            boolean isExamLoaded = TableLoader.loadAllExams(tblExams);
            if (!isExamLoaded) {
                new Alert(Alert.AlertType.INFORMATION, "No any exam scheduled yet").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void btnManageSubjectOnAction(ActionEvent actionEvent) throws IOException {
         Navigation.navigate(Routes.ACADEMIC_MANAGE_SUBJECTS,pane);
    }

    public void btnPaymentsOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ACADEMIC_PAYMENTS,pane);
    }
}
