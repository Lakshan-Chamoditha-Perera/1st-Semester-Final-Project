package lk.ijse.studentsmanagement.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import lk.ijse.studentsmanagement.util.Navigation;
import lk.ijse.studentsmanagement.util.Routes;

import java.io.IOException;

public class SendMailToGroupfromCounsilorFormController {

    @FXML
    private AnchorPane pane;

    @FXML
    private ComboBox<?> cmbCourse;

    @FXML
    private ComboBox<?> cmbBatch;

    @FXML
    void backClickOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.MAIL_SERVICE_COUNSELOR,pane);
    }

    @FXML
    void btnCancelOnAction(ActionEvent event) throws IOException {
        backClickOnAction(new ActionEvent());
    }

    @FXML
    void btnDoneClickOnAction(ActionEvent event) {

    }

    @FXML
    void btnSendOnAction(ActionEvent event) {

    }

}
