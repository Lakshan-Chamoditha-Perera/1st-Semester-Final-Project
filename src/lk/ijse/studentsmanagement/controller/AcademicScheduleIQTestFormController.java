package lk.ijse.studentsmanagement.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import lk.ijse.studentsmanagement.util.Navigation;
import lk.ijse.studentsmanagement.util.Routes;

import javax.swing.text.NavigationFilter;
import java.io.IOException;

public class AcademicScheduleIQTestFormController {

    @FXML
    private AnchorPane pane;

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

    public void btnViewOnAction(ActionEvent actionEvent) {
    }
}
