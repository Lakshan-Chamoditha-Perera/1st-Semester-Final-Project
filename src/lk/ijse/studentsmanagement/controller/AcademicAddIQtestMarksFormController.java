package lk.ijse.studentsmanagement.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import lk.ijse.studentsmanagement.util.Navigation;
import lk.ijse.studentsmanagement.util.Routes;

import javax.swing.text.NavigationFilter;
import java.io.IOException;

public class AcademicAddIQtestMarksFormController {

    public AnchorPane pane;

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.EXAMS,pane);
    }

}
