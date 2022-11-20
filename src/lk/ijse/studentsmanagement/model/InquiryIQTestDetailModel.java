package lk.ijse.studentsmanagement.model;

import lk.ijse.studentsmanagement.to.InquiryIQTestDetail;
import lk.ijse.studentsmanagement.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class InquiryIQTestDetailModel {
    public static boolean addInquiryTestDetail(InquiryIQTestDetail inquiryIQTestDetail) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(
                "INSERT INTO inquiry_iqTest_Detail VALUES(?,?,?)",
                inquiryIQTestDetail.getStudentId(),
                inquiryIQTestDetail.getTestId(),
                inquiryIQTestDetail.getResult()
        );
    }

    public static ArrayList<InquiryIQTestDetail> getInquiryIQTestList() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM inquiry_iqTest_Detail");
        ArrayList<InquiryIQTestDetail> list = new ArrayList<>();
        while(resultSet.next()){
            list.add(new InquiryIQTestDetail(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3)
            ));
        }
        return list;
    }
}
