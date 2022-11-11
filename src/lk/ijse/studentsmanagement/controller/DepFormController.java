package lk.ijse.studentsmanagement.controller;

import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.studentsmanagement.util.Navigation;
import lk.ijse.studentsmanagement.util.Routes;

import java.io.IOException;

public class DepFormController {
    public AnchorPane pane;

    public void backClickOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.COURSES,pane);

    }
}
