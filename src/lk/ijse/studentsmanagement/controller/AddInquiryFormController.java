package lk.ijse.studentsmanagement.controller;

import com.jfoenix.controls.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import lk.ijse.studentsmanagement.util.Navigation;
import lk.ijse.studentsmanagement.util.Routes;

import java.io.IOException;
import java.sql.Date;

public class AddInquiryFormController {

    public JFXComboBox cmbExamDates;
    public JFXButton btnPayment;
    @FXML
    private AnchorPane pane;

    @FXML
    private JFXDatePicker datePicker;

    @FXML
    private JFXRadioButton rBtnMale;

    @FXML
    private ToggleGroup gender;

    @FXML
    private JFXTextField txtId;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtMobile;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtCity;

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnCancel;

    @FXML
    private JFXButton btnAddIQTest;

    @FXML
    void backClickOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.INQUIRIES, pane);


    }

    @FXML
    void btnAddOnAction(ActionEvent event) {

    }


}
