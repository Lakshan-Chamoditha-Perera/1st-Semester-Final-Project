package lk.ijse.studentsmanagement.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import lk.ijse.studentsmanagement.util.Navigation;
import lk.ijse.studentsmanagement.util.Routes;

import java.io.IOException;


public class MailsCounselorFormControler {

    @FXML
    private AnchorPane mainPain;

    @FXML
    void sendToRegisteredGroupOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.SendMailToGroupfromCounsilor,mainPain);
    }

    @FXML
    void sendToRegisteredStudentOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.SendMailToStudentfromCounsilor,mainPain);
    }

}
