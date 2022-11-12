package lk.ijse.studentsmanagement.controller;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lk.ijse.studentsmanagement.util.Navigation;
import lk.ijse.studentsmanagement.util.Routes;

import java.io.IOException;

public class AcademicManageExamsFormController {

    public AnchorPane pane;
    @FXML
    private JFXTextField txtExamID;

    @FXML
    private JFXTextField txtDescription;

    @FXML
    private JFXDatePicker cmbDate;

    @FXML
    private JFXTextField txtLab;

    @FXML
    private JFXTextField txtBatchId;

    @FXML
    private JFXTextField txtSubject;

    @FXML
    private Label lblExamID;

    @FXML
    private JFXTextField txtCourseId;

    @FXML
    void backClickOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.EXAMS,pane);

    }

    @FXML
    void btnDeleteClickonAction(ActionEvent event) {

    }

    @FXML
    void btnSearchClickOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateClickOnAction(ActionEvent event) {

    }

}
