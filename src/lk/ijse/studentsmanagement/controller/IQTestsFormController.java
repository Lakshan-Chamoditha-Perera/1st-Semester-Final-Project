package lk.ijse.studentsmanagement.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import lk.ijse.studentsmanagement.autogenerater.AutoGenerateID;
import lk.ijse.studentsmanagement.comboLoad.ComboLoader;
import lk.ijse.studentsmanagement.comboLoad.TableLoader;
import lk.ijse.studentsmanagement.model.IQTestModel;
import lk.ijse.studentsmanagement.model.InquiryIQTestDetailModel;
import lk.ijse.studentsmanagement.model.InquiryModel;
import lk.ijse.studentsmanagement.model.TestPaymentModel;
import lk.ijse.studentsmanagement.util.RegExPatterns;
import lk.ijse.studentsmanagement.smtp.Mail;
import lk.ijse.studentsmanagement.tblModels.IQTestTM;
import lk.ijse.studentsmanagement.tblModels.InquiryIQTestDetailTM;
import lk.ijse.studentsmanagement.to.IQTest;
import lk.ijse.studentsmanagement.to.Inquiry;
import lk.ijse.studentsmanagement.to.InquiryIQTestDetail;
import lk.ijse.studentsmanagement.to.TestPayment;

import javax.mail.MessagingException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class IQTestsFormController implements Initializable {
    public TableView tblIQTestDetail;
    public TableColumn colTestID;
    public TableColumn colTestDate;
    public TableColumn colTestTime;
    public TableColumn colTestLab;
    public JFXButton btnPayment;
    public TableColumn colStdID;
    public TableView tblIQTestInquiryDetail;
    public TableColumn colExamID;
    public TableColumn colResults;
    public TableColumn colAmount;
    public Label lblPaymentID;
    public Label lblInvalidStdID;
    public Label lblSelectExamID;
    public JFXTextField txtStudentID;
    public JFXComboBox cmbExamDate;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblInvalidStdID.setVisible(false);
        lblSelectExamID.setVisible(false);
        try {
            addIQTestDetail();
            addInquiryIQTestDetail();
            AutoGenerateID.setLblPaymentID(lblPaymentID);
            boolean isIqExamDatesLoaded = ComboLoader.loadIQExamDatesComboBox(cmbExamDate);
            if (!isIqExamDatesLoaded) {
                new Alert(Alert.AlertType.INFORMATION, "No any exam dates").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, String.valueOf(e)).show();
        }
    }

    private void addInquiryIQTestDetail(){
        try{
            AutoGenerateID.setLblPaymentID(lblPaymentID);
            colExamID.setCellValueFactory(new PropertyValueFactory<>("testId"));
            colResults.setCellValueFactory(new PropertyValueFactory<>("result"));
            colStdID.setCellValueFactory(new PropertyValueFactory<>("studentId"));
            ArrayList<InquiryIQTestDetail> inquiryIQTestDetailArrayList = InquiryIQTestDetailModel.getInquiryIQTestList();
            ObservableList<InquiryIQTestDetailTM> observableArrayList = FXCollections.observableArrayList();
            for (InquiryIQTestDetail ele : inquiryIQTestDetailArrayList) {
                observableArrayList.add(
                        new InquiryIQTestDetailTM(
                                ele.getStudentId(),
                                ele.getTestId(),
                                ele.getResult()
                        )
                );
            }
            tblIQTestInquiryDetail.setItems(observableArrayList);

        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, String.valueOf(e)).show();
        }
    }

    private void addIQTestDetail() throws SQLException, ClassNotFoundException {
        colTestID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTestDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTestTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colTestLab.setCellValueFactory(new PropertyValueFactory<>("lab"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        TableLoader.method(tblIQTestDetail);
    }

    public void cmbExamIDOnMouseClicked(MouseEvent mouseEvent) {
        lblSelectExamID.setVisible(false);
    }

    public void btnPaymentOnAction(ActionEvent actionEvent) {
        try {
            if (RegExPatterns.getIdPattern().matcher(txtStudentID.getText()).matches()) {
                if (isExists(txtStudentID.getText())) {
                    if (cmbExamDate.getValue() != null) {
                        boolean isAdded = registerToNewIQTest();
                        String text = (isAdded) ? "added" : "error";
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, text);
                        alert.show();
                        addInquiryIQTestDetail();
                        if (isAdded) {
                            ObservableList<IQTestTM> items = tblIQTestDetail.getItems();
                            IQTestTM iqTest = new IQTestTM();
                            for (IQTestTM ele : items) {
                                if (String.valueOf(ele.getDate()).equals(cmbExamDate.getValue().toString())) {
                                    iqTest.setAmount(ele.getAmount());
                                    iqTest.setId(ele.getId());
                                    iqTest.setTime(ele.getTime());
                                    iqTest.setLab(ele.getLab());
                                    iqTest.setDate(ele.getDate());
                                    System.out.println(5);
                                }
                            }
                            String msg = "\t \t \t WELCOME TO INSTITUTE OF JAVA AND SOFTWARE ENGINEERING \n" +
                                    "\nPayment ID  " + lblPaymentID.getText() +
                                    "                Payment Date " + LocalDate.now() +
                                    "\nNIC   :" + txtStudentID.getText() +
                                    "\nTotal Amount = Rs." + iqTest.getAmount() +
                                    "\n \n----YOUR TEST DETAILS----" +
                                    "\nTEST ID    : " + iqTest.getId() +
                                    "\nDate       : " + iqTest.getDate() +
                                    "\nLab        : " + iqTest.getLab() +
                                    "\nStart Time : " + iqTest.getTime() +
                                    "\n\nThank You!...";
                            Inquiry inquiry = InquiryModel.getEmail(new Inquiry(txtStudentID.getText()));
                            if (inquiry != null) {
                                Mail.outMail(msg, inquiry.getEmail(), "OFFICIAL INQUIRY PAYMENT RECEIPT - INSTITUTE OF JAVA AND SOFTWARE ENGINEERING ");
                            }
                        }
                    } else {
                        lblSelectExamID.setVisible(true);
                    }
                } else {
                    new Alert(Alert.AlertType.ERROR, "Student not exists. Please register first").show();
                }
            } else {
                txtStudentID.setFocusColor(Color.RED);
                lblInvalidStdID.setVisible(true);
            }
        } catch (SQLException | ClassNotFoundException | MessagingException e) {
            new Alert(Alert.AlertType.ERROR, String.valueOf(e)).show();
        }
    }

    private boolean isExists(String studentID) throws SQLException, ClassNotFoundException {
        return InquiryModel.searchInquiry(new Inquiry(studentID)) != null;
    }

    private boolean registerToNewIQTest() throws SQLException, ClassNotFoundException {
        IQTest iqTestDetails = IQTestModel.getIQTestDetails(cmbExamDate.getValue().toString());
        //added
        return TestPaymentModel.addTestPaymentTransaction(
                new TestPayment(
                        lblPaymentID.getText(),
                        txtStudentID.getText(),
                        Date.valueOf(LocalDate.now()),
                        "Test Payment",
                        iqTestDetails.getAmount(),
                        iqTestDetails.getId(),
                        new InquiryIQTestDetail(
                                txtStudentID.getText(),
                                iqTestDetails.getId(),
                                "not-added"
                        )
                )
        );
    }

    public void txtStudentIDOnMouseClicked(MouseEvent mouseEvent) {
        lblInvalidStdID.setVisible(false);
    }
}