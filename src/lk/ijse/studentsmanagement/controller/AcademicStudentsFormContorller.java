package lk.ijse.studentsmanagement.controller;

import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.studentsmanagement.util.Navigation;
import lk.ijse.studentsmanagement.util.Routes;

import java.io.IOException;

public class AcademicStudentsFormContorller {
    public AnchorPane pane2;
    public AnchorPane pane;

    public void backClickOnAction(ActionEvent actionEvent) throws IOException {
        //Navigation.navigate(Routes,pane);
    }

    public void btnAddMarksOnAction(ActionEvent actionEvent) {
    }

    public void btnAddExamsOnAction(ActionEvent actionEvent) {
    }

    public void btnTranscriptOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ACADEMIC_TRANSCRIPT,pane);
    }

    public void btnViewStudentsDetailsOnAction(ActionEvent actionEvent) throws IOException {

    }
}
