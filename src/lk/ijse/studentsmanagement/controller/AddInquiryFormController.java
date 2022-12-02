package lk.ijse.studentsmanagement.controller;

import com.jfoenix.controls.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import lk.ijse.studentsmanagement.comboLoad.ComboLoader;
import lk.ijse.studentsmanagement.model.IQTestModel;
import lk.ijse.studentsmanagement.model.InquiryModel;
import lk.ijse.studentsmanagement.util.RegExPatterns;
import lk.ijse.studentsmanagement.smtp.Mail;
import lk.ijse.studentsmanagement.to.IQTest;
import lk.ijse.studentsmanagement.to.Inquiry;
import lk.ijse.studentsmanagement.to.InquiryIQTestDetail;
import lk.ijse.studentsmanagement.to.TestPayment;
import lk.ijse.studentsmanagement.util.Navigation;
import lk.ijse.studentsmanagement.util.Routes;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import javax.mail.MessagingException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.ResourceBundle;

import static lk.ijse.studentsmanagement.autogenerater.AutoGenerateID.setLblPaymentID;

public class AddInquiryFormController implements Initializable {
    public JFXComboBox<String> cmbExamDates;
    public Label lblInvalidID;
    public Label lblDate;
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
        lblTestTime.setText(iqTestDetails.getTime().toString());
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
    void txtIdOnMouseClicked(MouseEvent event) {
        lblInvalidID.setVisible(false);
    }

    @FXML
    void txtMobileOnMouseClicked(MouseEvent event) {
        lblInvalidMobile.setVisible(false);
    }

    @FXML
    void txtNameOnMouseClicked(MouseEvent event) {
        lblInvalidName.setVisible(false);
    }

    @FXML
    void btnAddOnAction(ActionEvent event) {
        if (RegExPatterns.getIdPattern().matcher(txtId.getText()).matches()) {
            if (RegExPatterns.getNamePattern().matcher(txtName.getText()).matches()) {
                if (RegExPatterns.getEmailPattern().matcher(txtEmail.getText()).matches()) {
                    if (RegExPatterns.getCityPattern().matcher(txtCity.getText()).matches()) {
                        if (RegExPatterns.getMobilePattern().matcher(txtMobile.getText()).matches()) {
                            if (cmbExamDates.getValue() != null) {
                                try {
                                    add();
                                } catch (SQLException | ClassNotFoundException e) {
                                    new Alert(Alert.AlertType.ERROR, String.valueOf(e)).show();
                                }
                            } else {
                                lblDate.setVisible(true);
                            }
                        } else {
                            txtMobile.setFocusColor(Color.valueOf("RED"));
                            lblInvalidMobile.setVisible(true);
                        }
                    } else {
                        txtCity.setFocusColor(Color.valueOf("RED"));
                        lblInvalidCity.setVisible(true);
                    }
                } else {
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

    private void add() throws SQLException, ClassNotFoundException{
        InquiryIQTestDetail inquiryIQTestDetail = new InquiryIQTestDetail(
                txtId.getText(),
                lblTestID.getText(),
                "not added"
        );
        //System.out.println(inquiryIQTestDetail);
        TestPayment testPayment = new TestPayment(
                lblPaymentID.getText(),
                txtId.getText(),
                java.sql.Date.valueOf(LocalDate.now()),
                "Test Payment",
                Double.parseDouble(lblAmount.getText()),
                lblTestID.getText(),
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
        if (isAdded) {
            printReport();
            String msg2 = "This email and any attachment transmitted herewith are confidential and is intended solely for the use of the individual or entity to which they are addressed and may contain information that is privileged or otherwise protected from disclosure. If you are not the intended recipient, you are hereby notified that disclosing, copying, distributing, or taking any action in reliance on this email and the information it contains is strictly prohibited. If you have received this email in error, please notify the sender immediately by reply email and discard all of its contents by deleting this email and the attachment, if any, from your system";
            String msg = "\t \t \t WELCOME TO INSTITUTE OF JAVA AND SOFTWARE ENGINEERING \n" +
                    "\nPayment ID  " + lblPaymentID.getText() +
                    "                Payment Date " + LocalDate.now() +
                    "\nNIC   :" + txtId.getText() +
                    "\nTotal Amount = Rs." + Double.parseDouble(lblAmount.getText()) +
                    "\n \n----YOUR TEST DETAILS----" +
                    "\nTEST ID    : " + lblTestID.getText() +
                    "\nDate       : " + cmbExamDates.getValue() +
                    "\nLab        : " + lblTestLab.getText() +
                    "\nStart Time : " + lblTestTime.getText() +
                    "\n\nThank You!...\n\n\n\n\n\n"+msg2;

            try {
                Mail.outMail(msg, txtEmail.getText(), "OFFICIAL INQUIRY PAYMENT RECEIPT - INSTITUTE OF JAVA AND SOFTWARE ENGINEERING ");
            } catch (MessagingException e) {
               new Alert(Alert.AlertType.ERROR,String.valueOf(e)).show();
            }
            new Alert(Alert.AlertType.INFORMATION,"Your Registration Succeed!").show();
            //clearAll();
            try {
                Navigation.navigate(Routes.ADD_STUDENT,pane);
            } catch (IOException e) {
                new Alert(Alert.AlertType.ERROR,String.valueOf(e)).show();
            }

        }
    }

    private void printReport() {
        HashMap hashMap = new HashMap<>();

        hashMap.put("receptNo",lblPaymentID.getText());
        hashMap.put("nic", txtId.getText());
        hashMap.put("name", txtName.getText());
        hashMap.put("testDate", lblDate.getText());
        hashMap.put("time", lblTestTime.getText());
        hashMap.put("lab", lblTestLab.getText());
        hashMap.put("total", lblAmount.getText());
        try {
            JasperReport compileReport = JasperCompileManager.compileReport(
                    JRXmlLoader.load(
                            getClass().getResourceAsStream(
                                    "/lk/ijse/studentsmanagement/report/OfficialReceipt.jrxml"
                            )
                    )
            );
            JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, hashMap, new JREmptyDataSource());
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException e) {
            new Alert(Alert.AlertType.INFORMATION, String.valueOf(e)).show();
        }
    }

    private void clearAll() throws SQLException, ClassNotFoundException {
        txtId.clear();
        lblTestID.setText(null);
        //  lblPaymentID.setText(null);
        txtId.clear();
        lblAmount.setText(null);
        txtName.clear();
        txtCity.clear();
        txtEmail.clear();
        txtMobile.clear();
        lblTestLab.setText(null);
        lblTestTime.setText(null);
        setLblPaymentID(lblPaymentID);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setLabelVisible();
        try {
            boolean isLoaded = ComboLoader.loadIQExamDatesComboBox(cmbExamDates);
            if (!isLoaded) {
                new Alert(Alert.AlertType.INFORMATION, "No any Exams").show();
            }
            setLblPaymentID(lblPaymentID);
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, String.valueOf(e)).show();
        }
    }

    private void setLabelVisible() {
        lblInvalidID.setVisible(false);
        lblInvalidCity.setVisible(false);
        lblInvalidEmail.setVisible(false);
        lblInvalidName.setVisible(false);
        lblInvalidMobile.setVisible(false);
        lblDate.setVisible(false);
    }
}