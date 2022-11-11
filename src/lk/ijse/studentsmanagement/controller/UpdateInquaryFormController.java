package lk.ijse.studentsmanagement.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import lk.ijse.studentsmanagement.util.Navigation;
import lk.ijse.studentsmanagement.util.Routes;

import java.io.IOException;

public class UpdateInquaryFormController {
    public AnchorPane pane;
    public JFXRadioButton rBtnMale;
    public JFXTextField txtId;
    public JFXTextField txtName;
    public JFXTextField txtMobile;
    public JFXTextField txtEmail;
    public JFXTextField txtCity;
    public JFXButton btnUpdate;
    public ToggleGroup gender;

    public void btnCancelOnAction(ActionEvent actionEvent) {
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
    }

    public void backClickOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.INQUIRIES, pane);
    }
}
