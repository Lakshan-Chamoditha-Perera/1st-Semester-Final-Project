package lk.ijse.studentsmanagement.controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lk.ijse.studentsmanagement.model.PaymentModel;
import lk.ijse.studentsmanagement.model.TestPaymentModel;
import lk.ijse.studentsmanagement.util.Navigation;
import lk.ijse.studentsmanagement.util.Routes;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AcademicPaymentsFormController implements Initializable {
    public AnchorPane subPanel;
    public JFXButton btnRegistration;
    public JFXButton btnInquiry;
    public Label lblTotal;
    public Label lblRegistrationPayments;
    public Label lblInquiryPayments;

    public void btnRegistrationOnAction() throws IOException {
        Navigation.navigate(Routes.ACADEMIC_REGISTRATION_PAYMENTS,subPanel);
    }

    public void btnInquiryOnAction() throws IOException {
        Navigation.navigate(Routes.ACADEMIC_INQUIRY_PAYMENTS,subPanel);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            getRegistrationSum();
            getInquirySum();
            lblTotal.setText(String.valueOf(Double.parseDouble(lblRegistrationPayments.getText())+
                    Double.parseDouble(lblInquiryPayments.getText())
            ));
        } catch (SQLException | ClassNotFoundException e) {
           new Alert(Alert.AlertType.ERROR,String.valueOf(e)).show();
        }
    }

    void getRegistrationSum() throws SQLException, ClassNotFoundException {
        double paymentsSum = PaymentModel.getPaymentsSum();
        lblRegistrationPayments.setText(String.valueOf(paymentsSum));
    }

    void getInquirySum() throws SQLException, ClassNotFoundException {
        double paymentsSum = TestPaymentModel.getPaymentsSum();
        lblInquiryPayments.setText(String.valueOf(paymentsSum));
    }

}
