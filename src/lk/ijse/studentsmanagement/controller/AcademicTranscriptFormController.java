package lk.ijse.studentsmanagement.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lk.ijse.studentsmanagement.util.Navigation;
import lk.ijse.studentsmanagement.util.Routes;

import java.io.IOException;

public class AcademicTranscriptFormController {

    @FXML
    private AnchorPane pane;

    @FXML
    private JFXTextField txtId;

    @FXML
    private JFXButton btnSearch;

    @FXML
    private Label lblStdName;

    @FXML
    private Label lblStdNic;

    @FXML
    private Label lblRegDate;

    @FXML
    private Label lblCourseId;

    @FXML
    private Label lblBatch;

    @FXML
    private Label lblCourseDuration;

    @FXML
    private Label lblCourseName;

    @FXML
    private JFXButton btnPrint;

    @FXML
    private JFXButton btnCancel;

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.ACADEMIC_STUDENTFORM,pane);
    }

}
