package lk.ijse.studentsmanagement.model;

import lk.ijse.studentsmanagement.db.DBconnection;
import lk.ijse.studentsmanagement.to.TestPayment;
import lk.ijse.studentsmanagement.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TestPaymentModel {
    public static String getLastPaymentID() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT id FROM test_Payment ORDER BY id DESC LIMIT 1");
        if (result.next()) { return result.getString(1); }
        return null;
    }
    public static boolean addTestPayment(TestPayment testPayment) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO test_Payment VALUES(?,?,?,?,?,?)",
                testPayment.getId(),
                testPayment.getStudentID(),
                testPayment.getDate(),
                testPayment.getRemark(),
                testPayment.getAmount(),
                testPayment.getIqTestId()
        );
    }
    public static boolean addTestPaymentTransaction(TestPayment testPayment) throws SQLException, ClassNotFoundException {
        try {
            DBconnection.getInstance().getConnection().setAutoCommit(false);
            if (addTestPayment(testPayment)) {
                if (InquiryIQTestDetailModel.addInquiryTestDetail(testPayment.getInquiryIQTestDetail())) {
                    DBconnection.getInstance().getConnection().commit();
                    return true;
                }
            }
            DBconnection.getInstance().getConnection().rollback();
            return false;
        } finally {
            DBconnection.getInstance().getConnection().setAutoCommit(true);
        }

    }
}