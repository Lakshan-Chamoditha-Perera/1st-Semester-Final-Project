package lk.ijse.studentsmanagement.controller;

import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.studentsmanagement.util.Navigation;
import lk.ijse.studentsmanagement.util.Routes;

import java.io.IOException;

public class AcademicExamFormController {
    public AnchorPane pane;

    public void backClickOnAction(ActionEvent actionEvent) {

    }

    public void btnAddMarksOnAction(ActionEvent actionEvent) {
        
    }

    public void btnAddExamsOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.SCHEDULE_EXAMS,pane);
    }

    public void btnIQTestOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.SCHEDULE_IQTEST,pane);
    }

    public void btnAddIQTestMarksOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ACADEMIC_ADDIQMARKS,pane);
    }

    public void btnViewResultsOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ACADEMIC_VIEW_RESULTS_BY_BATCH,pane);
    }

    public void btnManageExamsOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ACADEMIC_MANAGE_EXAMS,pane);
    }
}
