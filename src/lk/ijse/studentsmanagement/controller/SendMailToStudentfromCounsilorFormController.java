package lk.ijse.studentsmanagement.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import lk.ijse.studentsmanagement.util.Navigation;
import lk.ijse.studentsmanagement.util.Routes;

import javax.swing.text.NavigationFilter;
import java.io.IOException;

public class SendMailToStudentfromCounsilorFormController {

    public AnchorPane pane;

    @FXML
    void backClickOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.MAIL_SERVICE_COUNSELOR,pane);
    }

    @FXML
    void btnCancelOnAction(ActionEvent event) {

    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {

    }

    @FXML
    void btnSendOnAction(ActionEvent event) {

    }

}
