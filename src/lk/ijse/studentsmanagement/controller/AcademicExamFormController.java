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
}
