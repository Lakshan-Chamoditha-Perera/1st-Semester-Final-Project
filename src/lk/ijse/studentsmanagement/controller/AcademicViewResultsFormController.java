package lk.ijse.studentsmanagement.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import lk.ijse.studentsmanagement.util.Navigation;
import lk.ijse.studentsmanagement.util.Routes;

import java.io.IOException;

public class AcademicViewResultsFormController {

    public AnchorPane pane;
    @FXML
    private TableView<?> tblResults;

    @FXML
    private ComboBox<?> cmbBatch;

    @FXML
    private ComboBox<?> cmbExam;

    @FXML
    private Label lblSubjectId;

    @FXML
    private Label lblExamName;

    @FXML
    private Label lblExamDate;

    @FXML
    private Label lblExamLab;

    @FXML
    private Label lblStdCount;

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.EXAMS,pane);
    }

    @FXML
    void btnViewOnAction(ActionEvent event) {

    }

}
