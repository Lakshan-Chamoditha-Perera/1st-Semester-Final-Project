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
import lk.ijse.studentsmanagement.model.IQTestModel;
import lk.ijse.studentsmanagement.model.InquiryIQTestDetailModel;
import lk.ijse.studentsmanagement.model.InquiryModel;
import lk.ijse.studentsmanagement.model.TestPaymentModel;
import lk.ijse.studentsmanagement.regex.RegExPatterns;
import lk.ijse.studentsmanagement.tblModels.IQTestTM;
import lk.ijse.studentsmanagement.tblModels.InquiryIQTestDetailTM;
import lk.ijse.studentsmanagement.to.IQTest;
import lk.ijse.studentsmanagement.to.Inquiry;
import lk.ijse.studentsmanagement.to.InquiryIQTestDetail;
import lk.ijse.studentsmanagement.to.TestPayment;

import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    public JFXComboBox cmbExamID;

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
            ComboLoader.loadIQExamDatesComboBox(cmbExamDate);
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,String.valueOf(e)).show();
        }
    }
    private void addInquiryIQTestDetail() throws SQLException, ClassNotFoundException {
        AutoGenerateID.setLblPaymentID(lblPaymentID);
        colExamID.setCellValueFactory(new PropertyValueFactory<>("testId"));
        colResults.setCellValueFactory(new PropertyValueFactory<>("result"));
        colStdID.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        ArrayList<InquiryIQTestDetail> inquiryIQTestDetailArrayList = InquiryIQTestDetailModel.getInquiryIQTestList();
        ObservableList<InquiryIQTestDetailTM> observableArrayList= FXCollections.observableArrayList();
        for (InquiryIQTestDetail ele: inquiryIQTestDetailArrayList) {
            observableArrayList.add(
                    new InquiryIQTestDetailTM(
                            ele.getStudentId(),
                            ele.getTestId(),
                            ele.getResult()
                    )
            );
        }
        tblIQTestInquiryDetail.setItems(observableArrayList);
    }
    private void addIQTestDetail() throws SQLException, ClassNotFoundException {
        colTestID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTestDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTestTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colTestLab.setCellValueFactory(new PropertyValueFactory<>("lab"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        ArrayList<IQTest> iqTestList = IQTestModel.getIQTestList();
        ObservableList<IQTestTM> observableArrayList= FXCollections.observableArrayList();
        for (IQTest ele: iqTestList) {
            observableArrayList.add(
                    new IQTestTM(
                            ele.getId(),
                            ele.getDate(),
                            ele.getTime(),
                            ele.getLab(),
                            ele.getAmount()
                    )
            );
        }
        tblIQTestDetail.setItems(observableArrayList);
    }
    public void cmbExamIDOnMouseClicked(MouseEvent mouseEvent) {
        lblSelectExamID.setVisible(false);
    }
    public void btnPaymentOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if(RegExPatterns.getIdPattern().matcher(txtStudentID.getText()).matches()){
            if(isExists(txtStudentID.getText())) {
                if (cmbExamDate.getValue() != null) {
                    String text = (registerToNewIQTest()) ? "added" : "error";
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, text);
                    alert.show();
                    addInquiryIQTestDetail();
                } else {
                    lblSelectExamID.setVisible(true);
                }
            }else{
             new Alert(Alert.AlertType.ERROR,"Student not exists. Please register first").show();
            }
        }else{
            txtStudentID.setFocusColor(Color.RED);
            lblInvalidStdID.setVisible(true);
        }
    }
    private boolean isExists(String studentID) throws SQLException, ClassNotFoundException {
      return InquiryModel.searchInquiry(new Inquiry(studentID)) != null;
    }
    private boolean registerToNewIQTest() throws SQLException, ClassNotFoundException {
        IQTest iqTestDetails = IQTestModel.getIQTestDetails(cmbExamDate.getValue().toString());
        //added
        return TestPaymentModel.addTestPaymentTransaction(new TestPayment(
                lblPaymentID.getText(),
                txtStudentID.getText(),
                new SimpleDateFormat("dd-MM-20yy").format(new Date()),
                "Test Payment",
                iqTestDetails.getAmount(),
                iqTestDetails.getId(),
                new InquiryIQTestDetail(
                        txtStudentID.getText(),
                        iqTestDetails.getId(),
                        "not-added"
                )
        ));
    }
    public void txtStudentIDOnMouseClicked(MouseEvent mouseEvent) {lblInvalidStdID.setVisible(false);}
}