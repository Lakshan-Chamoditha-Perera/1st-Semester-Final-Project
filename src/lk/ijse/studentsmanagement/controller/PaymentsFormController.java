package lk.ijse.studentsmanagement.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import lk.ijse.studentsmanagement.autogenerater.AutoGenerateID;
import lk.ijse.studentsmanagement.comboLoad.TableLoader;
import lk.ijse.studentsmanagement.model.PaymentModel;
import lk.ijse.studentsmanagement.util.RegExPatterns;
import lk.ijse.studentsmanagement.tblModels.PaymentsTM;
import lk.ijse.studentsmanagement.to.Payment;
import lk.ijse.studentsmanagement.util.Navigation;
import lk.ijse.studentsmanagement.util.Routes;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.ResourceBundle;

public class PaymentsFormController implements Initializable {
    public Label lblPaymentID;
    public JFXTextField txtRegistrationId;
    public JFXTextField txAmount;
    public JFXTextField txtRemark;
    public JFXButton btnPayment;
    public Label lblDate;
    public JFXTextField txtSearchRegID;
    public Label lblInvalidRegistration;
    public Label lblInValidRemark;
    public Label lblInvalidAmount;
    public AnchorPane pane;
    public TableColumn colPaymentID;
    public TableView <PaymentsTM>tblPayment;
    public TableColumn colType;
    public TableColumn colRemark;
    public TableColumn colAmount;
    public TableColumn colDate;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            lblInvalidAmount.setVisible(false);
            lblInvalidRegistration.setVisible(false);
            lblInValidRemark.setVisible(false);
            AutoGenerateID.setLblRegPaymentID(lblPaymentID);
            lblDate.setText(Date.valueOf(LocalDate.now()).toString());
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }

        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colPaymentID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colRemark.setCellValueFactory(new PropertyValueFactory<>("remark"));
    }

    public void btnViewPaymentsOnAcion(ActionEvent actionEvent) {
        try{
            if(txtSearchRegID.getText()!=null){
                TableLoader.loadRegistrationPayments(tblPayment,txtSearchRegID.getText());
            }else{
                new Alert(Alert.AlertType.ERROR,"Invalid ID").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.INFORMATION, String.valueOf(e)).show();
        }
    }

    public void btnPayments(ActionEvent actionEvent){
       try{
           if(RegExPatterns.getRegistrationIdPattern().matcher(txtRegistrationId.getText()).matches()){
               if(RegExPatterns.getNamePattern().matcher(txtRemark.getText()).matches()){
                   if(RegExPatterns.getDoublePattern().matcher(txAmount.getText()).matches()){
                       boolean isAdded = PaymentModel.addPayment(
                               new Payment(
                                       lblPaymentID.getText(),
                                       txtRegistrationId.getText(),
                                       "fee",
                                       txtRemark.getText(),
                                       Double.parseDouble(txAmount.getText()),
                                       Date.valueOf(lblDate.getText())
                               )
                       );
                       if(isAdded){
                           Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Payment Success! Do you want to print invoice?", ButtonType.OK,ButtonType.NO);
                           TableLoader.loadRegistrationPayments(tblPayment,txtRegistrationId.getText());
                           alert.showAndWait();
                           ButtonType result = alert.getResult();
                           if(!result.getButtonData().isCancelButton()){
                              printReport();
                           }
                           Navigation.navigate(Routes.PAYMENTS, pane);
                       }else{
                           new Alert(Alert.AlertType.ERROR,"Payment not Done").show();
                       }
                   }else{
                       txAmount.setFocusColor(Color.RED);
                       new Alert(Alert.AlertType.ERROR,"Enter Amount").show();
                   }
               }else{
                   txtRemark.setFocusColor(Color.RED);
                   new Alert(Alert.AlertType.ERROR,"Enter Remark").show();
               }
           }else{
               txtRegistrationId.setFocusColor(Color.RED);
               new Alert(Alert.AlertType.ERROR,"Enter Registration ID First!").show();
           }
       } catch (ClassNotFoundException | IOException | SQLException e) {
           new Alert(Alert.AlertType.INFORMATION, String.valueOf(e)).show();
       }
    }
    private void printReport() {
        HashMap hashMap = new HashMap<>();

        hashMap.put("paymentId", lblPaymentID.getText());
        hashMap.put("regId", txtRegistrationId.getText());
        hashMap.put("remark", txtRemark.getText());
        hashMap.put("amount", txAmount.getText());
        hashMap.put("total", txAmount.getText());
        try {
            JasperReport compileReport = JasperCompileManager.compileReport(
                    JRXmlLoader.load(
                            getClass().getResourceAsStream(
                                    "/lk/ijse/studentsmanagement/report/PaymentReceipts.jrxml"
                            )
                    )
            );
            JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, hashMap, new JREmptyDataSource());
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException e) {
            new Alert(Alert.AlertType.INFORMATION, String.valueOf(e)).show();
        }
    }

    public void remarkOnMouseClick(MouseEvent mouseEvent) {
        lblInValidRemark.setVisible(false);
    }
    public void amountOnMouseClicked(MouseEvent mouseEvent) {
        lblInvalidAmount.setVisible(false);
    }
    public void regOnMouseClick(MouseEvent mouseEvent) {
        lblInvalidRegistration.setVisible(false);
    }
}
