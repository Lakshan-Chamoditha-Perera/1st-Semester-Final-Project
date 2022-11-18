package lk.ijse.studentsmanagement.model;

import lk.ijse.studentsmanagement.to.IQTest;
import lk.ijse.studentsmanagement.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class IQTestModel {
    public static ArrayList<IQTest> getIQTestList() throws SQLException, ClassNotFoundException {
        ArrayList<IQTest> list = new ArrayList<>();
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM iqTest");
        while(resultSet.next()){
            list.add(
                    new IQTest(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            Double.parseDouble(resultSet.getString(5)))
            );
        }
        return list;
    }

    public static IQTest getIQTestDetails(String date) throws SQLException, ClassNotFoundException {
         ResultSet resultSet =  CrudUtil.execute("SELECT * FROM iqTest WHERE date = ?", date);
         if (resultSet.next()){
               return new IQTest(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        Double.parseDouble(resultSet.getString(5))
                    );
         }
         return null;
    }

}
