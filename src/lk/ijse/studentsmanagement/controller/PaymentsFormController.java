package lk.ijse.studentsmanagement.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.mysql.cj.jdbc.IterateBlock;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.Loader;
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
import lk.ijse.studentsmanagement.tblModels.PaymentsTM;
import lk.ijse.studentsmanagement.to.Payment;
import lk.ijse.studentsmanagement.to.Registration;
import lk.ijse.studentsmanagement.util.Navigation;
import lk.ijse.studentsmanagement.util.Routes;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
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
            throw new RuntimeException(e);
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
            throw new RuntimeException(e);
        }
    }

    public void btnPayments(ActionEvent actionEvent){
       try{
           if(txtRegistrationId.getText()!=null){
               if(txtRemark.getText() != null){
                   if(txAmount.getText()!=null){
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
                           if(alert.getAlertType().equals(ButtonType.OK)){
                               /////////
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
           throw new RuntimeException(e);
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
