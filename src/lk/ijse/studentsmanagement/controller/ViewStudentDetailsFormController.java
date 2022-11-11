package lk.ijse.studentsmanagement.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import lk.ijse.studentsmanagement.util.Navigation;
import lk.ijse.studentsmanagement.util.Routes;

import java.io.IOException;

public class ViewStudentDetailsFormController {

    public JFXTextField txtMobile;
    @FXML
    private AnchorPane pane;
    @FXML
    private JFXTextField txtProgram;
    @FXML
    private JFXTextField txtRegistraion;
    @FXML
    private JFXTextField txtId;
    @FXML
    private JFXTextField txtName;
    @FXML
    private JFXTextField txtEmail;
    @FXML
    private JFXTextField txtCity;

    @FXML
    void backClickOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.INQUIRIES, pane);
    }
}
