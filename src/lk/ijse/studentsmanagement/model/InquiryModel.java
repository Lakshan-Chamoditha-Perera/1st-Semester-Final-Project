package lk.ijse.studentsmanagement.model;

import lk.ijse.studentsmanagement.db.DBconnection;
import lk.ijse.studentsmanagement.to.Inquiry;
import lk.ijse.studentsmanagement.util.CrudUtil;

import java.sql.SQLException;

public class InquiryModel {
    public static boolean addInquiry(Inquiry inquiry) throws SQLException, ClassNotFoundException {
        try {
            DBconnection.getInstance().getConnection().setAutoCommit(false);
            if(CrudUtil.execute(
                    "INSERT INTO inquiry VALUES (?,?,?,?,?,?,?,?)",
                        inquiry.getStudentID(),
                        inquiry.getName(),
                        inquiry.getCity(),
                        inquiry.getEmail(),
                        inquiry.getMobile(),
                        inquiry.getDate(),
                        inquiry.getGender(),
                        inquiry.getStatus()
            )){
                if (TestPaymentModel.addTestPayment(inquiry.getTestPayment())) {
                    if(InquiryIQTestDetailModel.addInquiryTestDetail(inquiry.getTestPayment().getInquiryIQTestDetail())){
                        DBconnection.getInstance().getConnection().commit();
                        return true;
                    }
                }
            }
            DBconnection.getInstance().getConnection().rollback();
            return false;
        } finally { DBconnection.getInstance().getConnection().setAutoCommit(true);}
    }
}