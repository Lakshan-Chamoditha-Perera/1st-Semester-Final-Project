package lk.ijse.studentsmanagement.controller;

import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import lk.ijse.studentsmanagement.model.IQTestModel;
import lk.ijse.studentsmanagement.model.InquiryModel;
import lk.ijse.studentsmanagement.model.TestPaymentModel;
import lk.ijse.studentsmanagement.regex.RegExPatterns;
import lk.ijse.studentsmanagement.to.IQTest;
import lk.ijse.studentsmanagement.to.Inquiry;
import lk.ijse.studentsmanagement.to.InquiryIQTestDetail;
import lk.ijse.studentsmanagement.to.TestPayment;
import lk.ijse.studentsmanagement.util.ComboLoad;
import lk.ijse.studentsmanagement.util.Navigation;
import lk.ijse.studentsmanagement.util.Routes;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Observable;
import java.util.ResourceBundle;

public class AddInquiryFormController implements Initializable {

    public JFXComboBox <String> cmbExamDates;
    public Label lblInvalidID;
    public Label lblDate;
    public Label lblExamID;
    public Label lblExamLab;
    public Label lblExamDate;
    public Label lblPaymentID;
    public Label lblTestID;
    public Label lblTestLab;
    public Label lblTestTime;
    public Label lblAmount;

    @FXML
    private Label lblInvalidName;

    @FXML
    private Label lblInvalidEmail;

    @FXML
    private Label lblInvalidCity;
    @FXML
    private Label lblInvalidMobile;
    @FXML
    private AnchorPane pane;
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
    void cmbExamDateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        lblDate.setVisible(false);

        IQTest iqTestDetails = IQTestModel.getIQTestDetails(cmbExamDates.getValue());
        lblTestID.setText(iqTestDetails.getId());
        lblTestLab.setText(iqTestDetails.getLab());
        lblTestTime.setText(iqTestDetails.getTime());
        lblAmount.setText(String.valueOf(iqTestDetails.getAmount()));

    }
    @FXML
    void txtCityOnMouseClicked(MouseEvent event) {
        lblInvalidCity.setVisible(false);
    }
    @FXML
    void txtEmailOnMouseClicked(MouseEvent event) {
        lblInvalidEmail.setVisible(false);
    }
    @FXML
    void txtIdOnMouseClicked(MouseEvent event) {lblInvalidID.setVisible(false);}
    @FXML
    void txtMobileOnMouseClicked(MouseEvent event) {
        lblInvalidMobile.setVisible(false);
    }

    @FXML
    void txtNameOnMouseClicked(MouseEvent event) {
        lblInvalidName.setVisible(false);
    }


    @FXML
    void btnAddOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        if (RegExPatterns.getIdPattern().matcher(txtId.getText()).matches()) {
            if (RegExPatterns.getNamePattern().matcher(txtName.getText()).matches()) {
                if(RegExPatterns.getEmailPattern().matcher(txtEmail.getText()).matches()){
                    if(RegExPatterns.getCityPattern().matcher(txtCity.getText()).matches()){
                        if(RegExPatterns.getMobilePattern().matcher(txtMobile.getText()).matches()){
                            if (cmbExamDates.getValue() != null){

                                InquiryIQTestDetail inquiryIQTestDetail = new InquiryIQTestDetail(
                                        txtId.getText(),
                                        lblTestID.getText(),
                                        "not added"
                                );
                                //System.out.println(inquiryIQTestDetail);
                                TestPayment testPayment = new TestPayment(
                                        lblPaymentID.getText(),
                                        txtId.getText(),
                                        cmbExamDates.getValue(),
                                        Double.parseDouble(lblAmount.getText()),
                                        inquiryIQTestDetail
                                );
                              //  System.out.println(testPayment);
                                Inquiry inquiry = new Inquiry(
                                        txtId.getText(),
                                        txtName.getText(),
                                        txtCity.getText(),
                                        txtEmail.getText(),
                                        txtMobile.getText(),
                                        new SimpleDateFormat("dd-MM-20yy").format(new Date()),
                                        (rBtnMale.isSelected()) ? "Male" : "Female",
                                        "not-registered",
                                        testPayment
                                );
                              //  System.out.println(inquiry);
                                boolean isAdded = InquiryModel.addInquiry(inquiry);
                                new Alert(Alert.AlertType.CONFIRMATION,(isAdded)? "ADDED":"ERROR").show();
                            }else {
                                lblDate.setVisible(true);
                            }
                        }else{
                            txtMobile.setFocusColor(Color.valueOf("RED"));
                            lblInvalidMobile.setVisible(true);
                        }
                    }else{
                        txtCity.setFocusColor(Color.valueOf("RED"));
                        lblInvalidCity.setVisible(true);
                    }
                }else{
                    txtEmail.setFocusColor(Color.valueOf("RED"));
                    lblInvalidEmail.setVisible(true);
                }
            } else {
                txtName.setFocusColor(Color.valueOf("RED"));
                lblInvalidName.setVisible(true);
            }
        } else {
            txtId.setFocusColor(Color.valueOf("RED"));
            lblInvalidID.setVisible(true);
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setLabelVisible();
        try {
            ComboLoader.loadComboBox(cmbExamDates,ComboLoad.IQTEST);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        setLblPaymentID();

    }
    private void setLabelVisible() {
        lblInvalidID.setVisible(false);
        lblInvalidCity.setVisible(false);
        lblInvalidEmail.setVisible(false);
        lblInvalidName.setVisible(false);
        lblInvalidMobile.setVisible(false);
        lblDate.setVisible(false);
    }
    private void setLblPaymentID() {
        try {
            String lastTestPaymentID= TestPaymentModel.getLastPaymentID();
            if(lastTestPaymentID==null){
                lblPaymentID.setText("TP00001");
            }else{
                String[] split=lastTestPaymentID.split("[TP]");
                int lastDigits=Integer.parseInt(split[2]);
                lastDigits++;
                lblPaymentID.setText(String.format("TP%05d", lastDigits));
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e+"").show();
        }
    }
}