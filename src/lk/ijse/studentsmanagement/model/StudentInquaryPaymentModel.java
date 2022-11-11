package lk.ijse.studentsmanagement.model;

import lk.ijse.studentsmanagement.to.StudentInquaryPayment;
import lk.ijse.studentsmanagement.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentInquaryPaymentModel {
    //    public static boolean addInquariyPayment(StudentInquaryPayment studentInquaryPayment) throws SQLException, ClassNotFoundException {
//
//        return CrudUtil.execute("INSERT INTO inquiryPayment ( ?, ?, ?)",
//
//                studentInquaryPayment.setStudentID();,
//                studentInquaryPayment.getAmount(),
//                studentInquaryPayment.getDate()
//        );
//    }
    public static String getNextPaymentId() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT paymentID FROM inquiryPayment ORDER BY paymentID DESC LIMIT 1");
        if (result.next()) {
            return generateNextPaymentId(result.getString(1));
        }
        return generateNextPaymentId(null);
    }

    private static String generateNextPaymentId(String currentPaymentId) {
        if (currentPaymentId != null) {
            String[] ids = currentPaymentId.split("D0");
            int id = Integer.parseInt(ids[1]);
            id += 1;
            return "P000" + id;
        }
        return "P0001";
    }
}
