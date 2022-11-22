package lk.ijse.studentsmanagement.autogenerater;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import lk.ijse.studentsmanagement.model.PaymentModel;
import lk.ijse.studentsmanagement.model.RegistrationModel;
import lk.ijse.studentsmanagement.model.TestPaymentModel;

import java.sql.SQLException;

public class AutoGenerateID {
    public static void setLblPaymentID(Label lblPaymentID) throws SQLException, ClassNotFoundException {

            String lastTestPaymentID= TestPaymentModel.getLastPaymentID();
            if(lastTestPaymentID==null){
                lblPaymentID.setText("T00001");
            }else{
                String[] split=lastTestPaymentID.split("[T]");
                int lastDigits=Integer.parseInt(split[1]);
                lastDigits++;
                lblPaymentID.setText(String.format("T%05d", lastDigits));
            }
    }

    public static void setLblRegPaymentID(Label lblPaymentID) throws SQLException, ClassNotFoundException {

        String lastTestPaymentID= PaymentModel.getLastPaymentID();
        if(lastTestPaymentID==null){
            lblPaymentID.setText("P000001");
        }else{
            String[] split=lastTestPaymentID.split("[P]");
            int lastDigits=Integer.parseInt(split[1]);
            lastDigits++;
            lblPaymentID.setText(String.format("P%06d", lastDigits));
        }
    }

    public static void setRegistrationID(Label lblRegistrationID) throws SQLException, ClassNotFoundException {
        String lastRegistrationId= RegistrationModel.getLastRegistrationID();
        if(lastRegistrationId==null){
            lblRegistrationID.setText("I000001");
        }else{
            String[] split=lastRegistrationId.split("[I]");
            int lastDigits=Integer.parseInt(split[1]);
            lastDigits++;
            lblRegistrationID.setText(String.format("I%06d", lastDigits));
        }
    }




}
