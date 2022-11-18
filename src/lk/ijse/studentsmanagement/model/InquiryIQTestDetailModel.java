package lk.ijse.studentsmanagement.model;

import lk.ijse.studentsmanagement.to.InquiryIQTestDetail;
import lk.ijse.studentsmanagement.util.CrudUtil;

import java.sql.SQLException;
public class InquiryIQTestDetailModel {
    public static boolean addInquiryTestDetail(InquiryIQTestDetail inquiryIQTestDetail) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(
                "INSERT INTO inquiry_iqTest_Detail VALUES(?,?,?)",
                inquiryIQTestDetail.getStudentId(),
                inquiryIQTestDetail.getTestId(),
                inquiryIQTestDetail.getResult()
        );
    }
}
