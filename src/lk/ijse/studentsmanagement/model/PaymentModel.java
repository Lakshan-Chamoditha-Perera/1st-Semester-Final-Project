package lk.ijse.studentsmanagement.model;

import lk.ijse.studentsmanagement.to.Payment;
import lk.ijse.studentsmanagement.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentModel {
    public static String getLastPaymentID() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT paymentId FROM payments ORDER BY paymentId DESC LIMIT 1");
        if (result.next()) {
            return result.getString(1);
        }
        return null;
    }
    public static boolean addPayment(Payment payment) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO payments VALUES (?,?,?,?,?,?)",
                payment.getId(),
                payment.getRegistrationId(),
                payment.getType(),
                payment.getRemark(),
                payment.getAmount(),
                payment.getDate()
        );
    }
}
