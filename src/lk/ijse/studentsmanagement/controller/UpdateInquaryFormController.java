package lk.ijse.studentsmanagement.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import lk.ijse.studentsmanagement.model.InquiryModel;
import lk.ijse.studentsmanagement.regex.RegExPatterns;
import lk.ijse.studentsmanagement.to.Inquiry;
import lk.ijse.studentsmanagement.util.Navigation;
import lk.ijse.studentsmanagement.util.Routes;

import javax.swing.text.rtf.RTFEditorKit;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UpdateInquaryFormController implements Initializable {
    public AnchorPane pane;
    public JFXTextField txtName;
    public JFXTextField txtMobile;
    public JFXTextField txtEmail;
    public JFXTextField txtCity;
    public JFXButton btnUpdate;
    public ToggleGroup gender;
    public JFXTextField txtID;
    public JFXButton btnSearch;
    public Label lblInvalidID;
    public JFXButton l;
    public JFXTextField txtGender;
    public Label lblInvalidName;
    public Label lblInvalidEmail;
    public Label lblInvalidCity;
    public Label lblInvalidMobile;
    public RadioButton rBtnMale;
    public RadioButton rBtnFemale;
    public JFXButton btnCancel;

    public void btnCancelOnAction(ActionEvent actionEvent) {
        clearAll();
        rBtnMale.setVisible(false);
        rBtnFemale.setVisible(false);
    }

    public void btnUpdateOnAction(ActionEvent actionEvent)  {
        if(RegExPatterns.getNamePattern().matcher(txtName.getText()).matches()){
            if(RegExPatterns.getEmailPattern().matcher(txtEmail.getText()).matches()){
                if(RegExPatterns.getCityPattern().matcher(txtCity.getText()).matches()){
                    if(RegExPatterns.getMobilePattern().matcher(txtMobile.getText()).matches()) {

                        boolean isUpdated = false;
                        try {
                            isUpdated = InquiryModel.updateInquiryDetails(
                                    new Inquiry(
                                            txtID.getText(),
                                            txtName.getText(),
                                            txtCity.getText(),
                                            txtEmail.getText(),
                                            txtMobile.getText(),
                                            (rBtnMale.isSelected()) ? "Male" : "Female")
                            );
                        } catch (SQLException | ClassNotFoundException e) {
                            new Alert(Alert.AlertType.ERROR,String.valueOf(e)).show();
                        }
                        if(isUpdated){
                            new Alert(Alert.AlertType.INFORMATION,"Inquiry Updated").show();
                            clearAll();
                        }else{
                            new Alert(Alert.AlertType.ERROR,"Inquiry not Updated").show();
                        }
                    }else {
                        txtMobile.setFocusColor(Color.RED);
                        lblInvalidMobile.setVisible(true);
                    }
                }else{
                    txtCity.setFocusColor(Color.RED);
                    lblInvalidCity.setVisible(true);
                }
            }else{
                txtEmail.setFocusColor(Color.RED);
                lblInvalidName.setVisible(true);
            }
        }else{
            txtName.setFocusColor(Color.RED);
            lblInvalidName.setVisible(true);
        }
    }

    public void backClickOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.INQUIRIES, pane);
    }

    public void txtIDOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        btnSearchOnAction(actionEvent);
    }

    public void btnSearchOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if(txtID.getText() != null){
            if(RegExPatterns.getIdPattern().matcher(txtID.getText()).matches()){
                Inquiry inquiry = InquiryModel.searchInquiry(new Inquiry(txtID.getText()));
                String text;
                if(!inquiry.equals(null)){
                    btnUpdate.setDisable(false);
                    rBtnMale.setVisible(true);
                    rBtnFemale.setVisible(true);
                    text ="exists";
                    btnUpdate.setDisable(false);
                    txtName.setText(inquiry.getName());
                    txtCity.setText(inquiry.getCity());
                    txtEmail.setText(inquiry.getEmail());
                    txtGender.setText(inquiry.getGender());
                    txtMobile.setText(inquiry.getMobile());
                }else{
                    text ="Not exists";
                    clearAll();
                }
                new Alert(Alert.AlertType.CONFIRMATION,text).show();
            }else{
                lblInvalidID.setVisible(true);
            }
        }else{
            new Alert(Alert.AlertType.ERROR,"Enter ID number First").show();
        }

    }

    private void clearAll() {
            txtID.clear();
            txtCity.clear();
            txtEmail.clear();
            txtMobile.clear();
            txtName.clear();
            txtGender.clear();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnUpdate.setDisable(true);
        lblInvalidID.setVisible(false);
        lblInvalidCity.setVisible(false);
        lblInvalidEmail.setVisible(false);
        lblInvalidMobile.setVisible(false);
        lblInvalidName.setVisible(false);
        rBtnMale.setVisible(false);
        rBtnFemale.setVisible(false);
    }

    public void txtIDOnMouseClicked(MouseEvent mouseEvent) {
        lblInvalidID.setVisible(false);
    }

    public void txtCityOnMouseClicked(MouseEvent mouseEvent) {
        lblInvalidCity.setVisible(false);
    }

    public void txtEmailOnMouseClicked(MouseEvent mouseEvent) {
        lblInvalidEmail.setVisible(false);
    }

    public void txtMobileNumberOnMouseClicked(MouseEvent mouseEvent) {
        lblInvalidMobile.setVisible(false);
    }

    public void txtNameOnMouseClicked(MouseEvent mouseEvent) {
        lblInvalidName.setVisible(false);
    }
}
