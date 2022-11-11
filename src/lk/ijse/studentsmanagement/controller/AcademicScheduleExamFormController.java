package lk.ijse.studentsmanagement.controller;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import lk.ijse.studentsmanagement.util.Navigation;
import lk.ijse.studentsmanagement.util.Routes;

import java.io.IOException;

public class AcademicScheduleExamFormController {

    @FXML
    private AnchorPane pane;

    @FXML
    private ComboBox<?> cmbCourse;

    @FXML
    private ComboBox<?> cmbBatch;

    @FXML
    private JFXTextField txtExamId;

    @FXML
    private ComboBox<?> cmbSubject;

    @FXML
    private JFXTextField txtDescription;

    @FXML
    private JFXDatePicker cmbDate;

    @FXML
    private JFXTextField txtLab;

    @FXML
    void backClickOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.EXAMS,pane);
    }

    @FXML
    void btnCancelOnAction(ActionEvent event) {

    }

    @FXML
    void btnSheduleOnAction(ActionEvent event) {

    }

}
