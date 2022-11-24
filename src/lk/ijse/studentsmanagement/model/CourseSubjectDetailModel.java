package lk.ijse.studentsmanagement.model;

import lk.ijse.studentsmanagement.to.CourseSubjectDetail;
import lk.ijse.studentsmanagement.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CourseSubjectDetailModel {
    public static ArrayList<CourseSubjectDetail> getCourseList(String batchId) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT courseId,subjectID from course_subject_detail where courseId = (SELECT courseId from batch where batchID = ?)", batchId);
        ArrayList<CourseSubjectDetail> list = new ArrayList<>();
        while (resultSet.next()){
            list.add(
                    new CourseSubjectDetail(
                    resultSet.getString(1),
                            resultSet.getString(2)
            ));
        }
        return list;
    }
}
