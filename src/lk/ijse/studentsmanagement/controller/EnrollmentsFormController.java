package lk.ijse.studentsmanagement.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import lk.ijse.studentsmanagement.comboLoad.ComboLoader;
import lk.ijse.studentsmanagement.model.BatchModel;
import lk.ijse.studentsmanagement.model.GardianModel;
import lk.ijse.studentsmanagement.model.RegistrationModel;
import lk.ijse.studentsmanagement.regex.RegExPatterns;
import lk.ijse.studentsmanagement.to.Batch;
import lk.ijse.studentsmanagement.to.Gardian;
import lk.ijse.studentsmanagement.to.Payment;
import lk.ijse.studentsmanagement.to.Registration;
import lk.ijse.studentsmanagement.util.Navigation;
import lk.ijse.studentsmanagement.util.Routes;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import static lk.ijse.studentsmanagement.autogenerater.AutoGenerateID.setLblRegPaymentID;
import static lk.ijse.studentsmanagement.autogenerater.AutoGenerateID.setRegistrationID;

public class EnrollmentsFormController implements Initializable {

    public Label lblSelectCourse;
    public Label lblInvalidEmail;
    public Label lblSelectDob;
    public Label lblSelectCourseFirst;
    public Label lblInvalidSearchDetail;
    public JFXTextField txtStdNic;
    public Label lblInvalidStdNic;
    @FXML
    private AnchorPane pane;

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
    private JFXRadioButton rBtnAL;

    @FXML
    private JFXRadioButton rBtnOL;

    @FXML
    private Label lblRegID;

    @FXML
    private JFXTextField txtPostalCode;

    @FXML
    private Label lblInvalidName;

    @FXML
    private Label lblInvalidAddress;

    @FXML
    private Label lblInvalidCity;

    @FXML
    private Label lblInvalidPostalCode;

    @FXML
    private Label lblInvalidMobileNumber;

    @FXML
    private Label lblInvalidMobileNumber1;

    @FXML
    private Label lblInvalidSchool;

    @FXML
    private JFXRadioButton rBtnYes;

    @FXML
    private ToggleGroup familyMember;

    @FXML
    private JFXRadioButton rBtnNo;

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
    private JFXTextField txtParentID;

    @FXML
    private JFXTextField txtSearchParent;

    @FXML
    private JFXButton btnSearch;

    @FXML
    private Label lblInvalidParentID;

    @FXML
    private Label lblInvalidParentName;

    @FXML
    private Label lblInvalidParentAddress;

    @FXML
    private Label lblInvalidParentMobile;

    @FXML
    private Label lblInvalidParentEmail;

    @FXML
    private Label lblInvalidParentDesignaion;

    @FXML
    private Label lblInvalidParentWorkingPlace;

    @FXML
    private Label lblPaymentID;

    @FXML
    private JFXTextField txtAmount;

    @FXML
    private JFXTextField txtRemark;

    @FXML
    private Label txtBatch;

    @FXML
    private JFXComboBox<String> cmbCourse;

    @FXML
    void btnEnrollClickOnAction(ActionEvent event) {
        Registration registration = isStdDetailCorrect();
        Gardian gardian;
        if(registration != null){
            if(rBtnNo.isSelected()){
                gardian = setGardianDetail();
                if(gardian != null){
                    System.out.println("gardian done");
                    if(!txtBatch.getText().equals("No any Ongoing Batch")){
                        registration.setPayment(setPaymentOb());
                        gardian.setRegistration(registration);
                        add(gardian);
                    }else{
                        new Alert(Alert.AlertType.INFORMATION,"Please select a course first").show();
                    }
                }
            }
            else{
                if(RegExPatterns.getOldIDPattern().matcher(txtParentID.getText()).matches()){
                    gardian = setGardianDetail();
                    if(gardian!=null){
                        if(!txtBatch.getText().equals("No any Ongoing Batch")){
                            registration.setPayment(setPaymentOb());
                           // gardian.setRegistration(registration);
                            try {
                                boolean flag = RegistrationModel.registrationPaymentTransaction(registration);
                                String text =(flag)?"DONE":"ERROR";
                                Navigation.navigate(Routes.ENROLLMENTS, pane);
                                new Alert(Alert.AlertType.INFORMATION,text).show();
                            } catch (SQLException | IOException | ClassNotFoundException e) {
                                new Alert(Alert.AlertType.INFORMATION,String.valueOf(e)).show();
                            }
                           // add(gardian);
                        }
                    }

                }else{
                    System.out.println("search parent first");
                    new Alert(Alert.AlertType.INFORMATION,"Search parent first").show();
                }
            }
        }
        else{
            new Alert(Alert.AlertType.INFORMATION, "Please enter registration details").show();
        }
    }

    private void add(Gardian gardian) {
        boolean isAdded = false;
        try {
            isAdded = GardianModel.addGardianT(gardian);
            String text ="NOT";
            if(isAdded){
                text = "ADDED";
                Navigation.navigate(Routes.ENROLLMENTS, pane);
            }
            new Alert(Alert.AlertType.INFORMATION,text).show();
        } catch (SQLException | IOException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, String.valueOf(e)).show();
        }


    }

    private Payment setPaymentOb() {
        return new Payment(
                lblPaymentID.getText(),
                lblRegID.getText(),
                "registration",
                "Registration Payment",
                Double.parseDouble(txtAmount.getText()),
                Date.valueOf(LocalDate.now())
        );
    }

    private Gardian setGardianDetail() {
        System.out.println("gardian regex");
            if(RegExPatterns.getOldIDPattern().matcher(txtParentID.getText()).matches()){
                System.out.println("Id check");
                if(RegExPatterns.getNamePattern().matcher(txtParentName.getText()).matches()){
                    System.out.println("name check");
                    if(RegExPatterns.getAddressPattern().matcher(txtParentAddress.getText()).matches()){
                        System.out.println("address check");
                        if(RegExPatterns.getMobilePattern().matcher(txtParentMobile.getText()).matches()){
                            System.out.println("mobile check");
                               if(RegExPatterns.getEmailPattern().matcher(txtParentEmail.getText()).matches()){
                                   System.out.println("email check");
                                   if(RegExPatterns.getNamePattern().matcher(txtParentDesignation.getText()).matches()){
                                       System.out.println("designation check");
                                       if(RegExPatterns.getNamePattern().matcher(txtParentWorkPlace.getText()).matches()){
                                           System.out.println("work place check");
                                                  return new Gardian(
                                                          txtParentID.getText(),
                                                          txtParentName.getText(),
                                                          txtParentAddress.getText(),
                                                          txtParentMobile.getText(),
                                                          txtParentEmail.getText(),
                                                          txtParentDesignation.getText(),
                                                          txtParentWorkPlace.getText()
                                                  );
                                       }else{
                                           lblInvalidParentWorkingPlace.setVisible(true);
                                           txtParentWorkPlace.setFocusColor(Color.RED);
                                       }
                                   }else{
                                       lblInvalidParentDesignaion.setVisible(true);
                                       txtParentDesignation.setFocusColor(Color.RED);
                                   }
                               }else{
                                   lblInvalidParentEmail.setVisible(true);
                                   txtParentEmail.setFocusColor(Color.RED);
                               }
                        }else{
                            lblInvalidParentMobile.setVisible(true);
                            txtParentMobile.setFocusColor(Color.RED);
                        }
                    }else{
                        lblInvalidParentAddress.setVisible(true);
                        txtParentAddress.setFocusColor(Color.RED);
                    }
                }else{
                    lblInvalidParentName.setVisible(true);
                    txtParentName.setFocusColor(Color.RED);
                }
            }else{
                lblInvalidParentID.setVisible(true);
                txtParentID.setFocusColor(Color.RED);
            }
            return null;
        }
    private Registration isStdDetailCorrect() {
        if (RegExPatterns.getNamePattern().matcher(txtStdName.getText()).matches()) {
           if(RegExPatterns.getIdPattern().matcher(txtStdNic.getText()).matches()){
               if (RegExPatterns.getAddressPattern().matcher(txtStdAddress.getText()).matches()) {
                   if (RegExPatterns.getCityPattern().matcher(txtStdCity.getText()).matches()) {
                       if (RegExPatterns.getPostalCodePattern().matcher(txtPostalCode.getText()).matches()) {
                           if (RegExPatterns.getMobilePattern().matcher(txtStdMobileNumber.getText()).matches()) {
                               if (RegExPatterns.getEmailPattern().matcher(txtStdEmail.getText()).matches()) {
                                   if (calDob.getValue() != null) {
                                       if (RegExPatterns.getNamePattern().matcher(txtSchool.getText()).matches()) {
                                           if (cmbCourse.getValue() != null) {
                                               return new Registration(

                                                       lblRegID.getText(),
                                                       txtStdNic.getText(),
                                                       txtBatch.getText(),
                                                       cmbCourse.getValue(),
                                                       txtParentID.getText(),
                                                       txtStdName.getText(),
                                                       txtStdAddress.getText(),
                                                       txtStdCity.getText(),
                                                       txtPostalCode.getText(),
                                                       txtStdMobileNumber.getText(),
                                                       txtStdEmail.getText(),
                                                       Date.valueOf(calDob.getValue()),
                                                       (rBtnMale.isSelected())?"Male":
                                                               "Female",
                                                       txtSchool.getText(),
                                                       (rBtnOL.isSelected())?"Ordinary Level":
                                                               (rBtnAL.isSelected())?"Advanced Level":
                                                                       (rBtnDiploma.isSelected())?"Diploma Level":
                                                                               (rBtnDegree.isSelected())?"Degree Level":
                                                                                       "Master",
                                                       "Active"

                                               );
                                           } else {
                                               lblSelectCourseFirst.setVisible(true);
                                           }
                                       } else {
                                           txtSchool.setFocusColor(Color.RED);
                                           lblInvalidSchool.setVisible(true);
                                       }
                                   } else {
                                       lblSelectDob.setVisible(true);
                                   }
                               } else {
                                   txtStdEmail.setFocusColor(Color.RED);
                                   lblInvalidEmail.setVisible(true);
                               }
                           } else {
                               txtStdMobileNumber.setFocusColor(Color.RED);
                               lblInvalidMobileNumber.setVisible(true);
                           }
                       } else {
                           txtPostalCode.setFocusColor(Color.RED);
                           lblInvalidPostalCode.setVisible(true);
                       }
                   } else {
                       txtStdCity.setFocusColor(Color.RED);
                       lblInvalidCity.setVisible(true);
                   }
               } else {
                   txtStdAddress.setFocusColor(Color.RED);
                   lblInvalidAddress.setVisible(true);
               }
           }else{
               txtStdNic.setFocusColor(Color.RED);
               lblInvalidStdNic.setVisible(true);
           }
        } else {
            txtStdName.setFocusColor(Color.RED);
            lblInvalidName.setVisible(true);
        }
        return null;
    }

    @FXML
    void btnSearchOnaction(ActionEvent event) throws SQLException, ClassNotFoundException {
        if(RegExPatterns.getOldIDPattern().matcher(txtSearchParent.getText()).matches()){
            Gardian gardianDetail = GardianModel.getGardianDetail(new Gardian(txtSearchParent.getText()));
            if(gardianDetail!=null){
                txtParentID.setText(gardianDetail.getId());
                txtParentName.setText(gardianDetail.getName());
                txtParentEmail.setText(gardianDetail.getEmail());
                txtParentMobile.setText(gardianDetail.getMobile());
                txtParentWorkPlace.setText(gardianDetail.getWorkingPlace());
                txtParentAddress.setText(gardianDetail.getAddress());
                txtParentDesignation.setText(gardianDetail.getDesignation());
            }else{
                new Alert(Alert.AlertType.ERROR,"Gardian not Found!").show();
            }
        }else{
         lblInvalidSearchDetail.setVisible(true);
         txtSearchParent.setFocusColor(Color.RED);
        }
    }

    @FXML
    void btnViewOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.VIEW_REGISTRATION, pane);
    }

    @FXML
    void cmbCourseOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        Batch lastOngoingBatch = BatchModel.getLastOngoingBatch(cmbCourse.getValue());
        if (lastOngoingBatch != null) {
            txtBatch.setText(lastOngoingBatch.getId());
        } else {
            txtBatch.setText("No any Ongoing Batch");
        }

    }

    @FXML
    void rBtnNoOnAction(ActionEvent event) {
        if (rBtnNo.isSelected()) {
            setTextEnable(false);
        }
    }

    private void setTextEnable(boolean flag) {
        txtParentID.setDisable(flag);
        txtParentName.setDisable(flag);
        txtParentAddress.setDisable(flag);
        txtParentMobile.setDisable(flag);
        txtParentEmail.setDisable(flag);
        txtParentDesignation.setDisable(flag);
        txtParentWorkPlace.setDisable(flag);
        txtSearchParent.setDisable(!flag);
        btnSearch.setDisable(!flag);
    }

    @FXML
    void rBtnYesOnAction(ActionEvent event) {
        if (rBtnYes.isSelected()) {
            setTextEnable(true);
        }
    }

    @FXML
    void txtParentAddressOnAction(MouseEvent event) {
        lblInvalidParentAddress.setVisible(false);
    }

    @FXML
    void txtParentDesignationOnAction(MouseEvent event) {
        lblInvalidParentDesignaion.setVisible(false);
    }

    @FXML
    void txtParentEmailOnAction(MouseEvent event) {
        lblInvalidParentEmail.setVisible(false);
    }

    @FXML
    void txtParentIDOnAction(MouseEvent event) {
        lblInvalidParentID.setVisible(false);
    }

    @FXML
    void txtParentMobileOnAction(MouseEvent event) {
        lblInvalidParentMobile.setVisible(false);
    }

    @FXML
    void txtParentNameOnAction(MouseEvent event) {
        lblInvalidParentName.setVisible(false);
    }

    @FXML
    void txtWorkingPlaceOnAction(MouseEvent event) {
        lblInvalidParentWorkingPlace.setVisible(false);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            ComboLoader.loadCoursesList(cmbCourse);
            setLblRegPaymentID(lblPaymentID);
            setRegistrationID(lblRegID);
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,String.valueOf(e)).show();
        }

    }

    public void txtStdAddressOnMouseClick(MouseEvent mouseEvent) {
        lblInvalidAddress.setVisible(false);
    }

    public void txtStdCityOnMouseClicked(MouseEvent mouseEvent) {
        lblInvalidCity.setVisible(false);
    }

    public void txtStdMobileOnMouseClicked(MouseEvent mouseEvent) {
        lblInvalidMobileNumber.setVisible(false);
    }

    public void txtStdEmailOnMouseClick(MouseEvent mouseEvent) {
        lblInvalidEmail.setVisible(false);
    }

    public void txtStdSchoolOnMouseClicked(MouseEvent mouseEvent) {
        lblInvalidSchool.setVisible(false);
    }

    public void txtStdNameOnMouseClicked(MouseEvent mouseEvent) {
        lblInvalidName.setVisible(false);
    }

    public void txtStdPostalCodeOnMouseClicked(MouseEvent mouseEvent) {
        lblInvalidPostalCode.setVisible(false);
    }

    public void calDobOnMouseClicked(MouseEvent mouseEvent) {
        lblSelectDob.setVisible(false);
    }

    public void cmbCourseOnMouseClicked(MouseEvent mouseEvent) {
        lblSelectCourseFirst.setVisible(false);
    }

    public void txtSearchOnMouseClicked(MouseEvent mouseEvent) {
        lblInvalidSearchDetail.setVisible(false);
    }

    public void txtStdNicOnAction(MouseEvent mouseEvent) {
        lblInvalidStdNic.setVisible(false);
    }
}



