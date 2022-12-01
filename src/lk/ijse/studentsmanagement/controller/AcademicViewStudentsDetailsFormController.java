package lk.ijse.studentsmanagement.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.studentsmanagement.comboLoad.TableLoader;
import lk.ijse.studentsmanagement.model.BatchModel;
import lk.ijse.studentsmanagement.model.RegistrationModel;
import lk.ijse.studentsmanagement.regex.RegExPatterns;
import lk.ijse.studentsmanagement.to.Registration;
import lk.ijse.studentsmanagement.util.Navigation;
import lk.ijse.studentsmanagement.util.Routes;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AcademicViewStudentsDetailsFormController implements Initializable {

    public AnchorPane pane;
    public JFXTextField txtID;
    public JFXTextField txtStdSchool;
    public JFXButton btnUpdate;
    public TableView tblPayments;
    public TableColumn colId;
    public TableColumn colRemark;
    public TableColumn colType;
    public TableColumn colAmount;
    public TableColumn colDate;
    public Label lblBatchID;
    public Label lblCourseID;
    public Label lblDate;
    public JFXTextField txtStdPostalCode;
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
    private JFXDatePicker calDob;

    @FXML
    private JFXTextField txtStdName;

    @FXML
    private JFXTextField txtParentMobile1;

    @FXML
    void backClickOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.ACADEMIC_STUDENTFORM, pane);
    }

    @FXML
    void btnSearchClickOnAction(ActionEvent event) {
        Registration registrationDetails = null;
        try {
            if (RegExPatterns.getRegistrationIdPattern().matcher(txtID.getText()).matches()) {
                registrationDetails = RegistrationModel.getRegistrationDetails(txtID.getText());
                if (registrationDetails != null) {
                    new Alert(Alert.AlertType.ERROR, "Students exists").show();
                    btnUpdate.setDisable(false);
                    loadPaymentsTable();
                    setRegistrationDetails(registrationDetails);
                    setBatchDetails(registrationDetails);
                } else {
                    new Alert(Alert.AlertType.ERROR, "Student Does Not exists").show();
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Invalid ID").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, String.valueOf(e)).show();
        }
    }

    private void loadPaymentsTable() throws SQLException, ClassNotFoundException {
        TableLoader.loadRegistrationPayments(tblPayments,txtID.getText());
    }
    private void setRegistrationDetails(Registration registrationDetails) {
        txtStdName.setText(registrationDetails.getName());
        txtStdAddress.setText(registrationDetails.getAddress());
        txtStdCity.setText(registrationDetails.getCity());
        txtStdMobileNumber.setText(registrationDetails.getMobile());
        calDob.setValue(registrationDetails.getDob().toLocalDate());
        txtStdSchool.setText(registrationDetails.getSchool());
        txtStdEmail.setText(registrationDetails.getEmail());
        txtStdPostalCode.setText(registrationDetails.getPostalCode());
    }
    private void setBatchDetails(Registration registrationDetails) {
        lblBatchID.setText(registrationDetails.getBatchId());
        lblCourseID.setText(registrationDetails.getCourseId());
    }
    @FXML
    void btnUpdateClickOnAction(ActionEvent event) {
        try {
            boolean isUpdated = RegistrationModel.updateRegistrationDetails(
                    new Registration(
                            txtID.getText(),
                            txtStdName.getText(),
                            txtStdAddress.getText(),
                            txtStdCity.getText(),
                            txtStdPostalCode.getText(),
                            txtStdMobileNumber.getText(),
                            txtStdEmail.getText(),
                            Date.valueOf(calDob.getValue()),
                            txtStdSchool.getText()
                    ));
            String text = (isUpdated) ? "Updated Done" : "Error";

            new Alert(Alert.AlertType.INFORMATION, text).show();
            Navigation.navigate(Routes.ACADEMIC_VIEW_STUDENT_DETAILS, pane);
        } catch (SQLException | ClassNotFoundException | IOException e) {
            new Alert(Alert.AlertType.ERROR, String.valueOf(e)).show();
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        btnUpdate.setDisable(true);

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colRemark.setCellValueFactory(new PropertyValueFactory<>("remark"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
    }
}
