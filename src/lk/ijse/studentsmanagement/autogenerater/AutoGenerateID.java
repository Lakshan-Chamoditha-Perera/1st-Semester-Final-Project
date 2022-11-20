package lk.ijse.studentsmanagement.autogenerater;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import lk.ijse.studentsmanagement.model.TestPaymentModel;

import java.sql.SQLException;

public class AutoGenerateID {
    public static void setLblPaymentID(Label lblPaymentID) throws SQLException, ClassNotFoundException {

            String lastTestPaymentID= TestPaymentModel.getLastPaymentID();
            if(lastTestPaymentID==null){
                lblPaymentID.setText("TP00001");
            }else{
                String[] split=lastTestPaymentID.split("[TP]");
                int lastDigits=Integer.parseInt(split[2]);
                lastDigits++;
                lblPaymentID.setText(String.format("TP%05d", lastDigits));
            }
    }
}
