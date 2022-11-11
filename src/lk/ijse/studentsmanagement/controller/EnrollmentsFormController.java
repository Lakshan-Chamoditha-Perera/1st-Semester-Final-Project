package lk.ijse.studentsmanagement.controller;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import lk.ijse.studentsmanagement.util.Navigation;
import lk.ijse.studentsmanagement.util.Routes;

import java.io.IOException;

public class EnrollmentsFormController {

    public AnchorPane pane;
    @FXML
    private JFXTextField txtStdAddress;

    @FXML
    private JFXTextField txtStdCity;

    @FXML
    private JFXTextField txtStdMobileNumber;

    @FXML
    private JFXTextField txtStdEmail;

    @FXML
    private JFXTextField txtSchool;

    @FXML
    private JFXTextField txtUniversity;

    @FXML
    private JFXRadioButton rBtnMale;

    @FXML
    private ToggleGroup gender;

    @FXML
    private JFXRadioButton rBtnFemale;

    @FXML
    private JFXDatePicker calDob;

    @FXML
    private JFXTextField txtStdName;

    @FXML
    private JFXRadioButton rBtnMaster;

    @FXML
    private ToggleGroup edu;

    @FXML
    private JFXRadioButton rBtnDegree;

    @FXML
    private JFXRadioButton rBtnDiploma;

    @FXML
    private JFXRadioButton rBtnOL;

    @FXML
    private JFXRadioButton rBtnGDSE;

    @FXML
    private ToggleGroup program;

    @FXML
    private JFXRadioButton rBtnCMJD;

    @FXML
    private JFXRadioButton rBtnDEP;

    @FXML
    private JFXRadioButton rBtnRWAD;

    @FXML
    private Label txtBatch;

    @FXML
    private JFXTextField txtParentAddress;

    @FXML
    private JFXTextField txtParentMobile;

    @FXML
    private JFXTextField txtParentName;

    @FXML
    private JFXTextField txtParentEmail;

    @FXML
    private JFXTextField txtParentDesignation;

    @FXML
    private JFXTextField txtParentWorkPlace;

    @FXML
    void rBtnAL(ActionEvent event) {

    }

    public void btnViewOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.VIEW_REGISTRATION,pane);
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
    }
}
