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
    void sendToRegisteredStudentOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.SEND_MAIL_TO_REGISTERED,mainPain);
    }

    public void sendToInquiryOnAction(ActionEvent actionEvent) {

    }
}
