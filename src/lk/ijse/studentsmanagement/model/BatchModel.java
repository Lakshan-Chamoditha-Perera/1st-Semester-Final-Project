package lk.ijse.studentsmanagement.model;

import lk.ijse.studentsmanagement.to.Batch;
import lk.ijse.studentsmanagement.util.CrudUtil;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BatchModel {
    public static Batch getLastOngoingBatch(String courseName) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("select * from batch where courseId = '" + courseName + "' order by batchID DESC LIMIT 1");
        if (resultSet.next()) {
            return new Batch(
                    resultSet.getString(1),
                    Integer.parseInt(resultSet.getString(2)),
                    resultSet.getString(3),
                    Double.parseDouble(resultSet.getString(4)),
                    Date.valueOf(resultSet.getString(5)),
                    Integer.parseInt(resultSet.getString(6))
            );
        }
        return null;
    }

    public static ArrayList<Batch> getBatchIDList(String courseName) throws SQLException, ClassNotFoundException {
        ArrayList<Batch> list = new ArrayList<>();
        ResultSet resultSet = CrudUtil.execute("SELECT batchId FROM batch where courseId = ?", courseName);
        while (resultSet.next()) {
            list.add(new Batch(resultSet.getString(1)));
        }
        return list;
    }

    public static ArrayList<Batch> getBatches(String course) throws SQLException, ClassNotFoundException {
        ArrayList<Batch> list = new ArrayList<>();
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM batch where courseId = ?", course);
        while (resultSet.next()) {
            list.add(
                    new Batch(
                    resultSet.getString(1),
                    Integer.parseInt(resultSet.getString(2)),
                    resultSet.getString(3),
                    Double.parseDouble(resultSet.getString(4)),
                    Date.valueOf(resultSet.getString(5)),
                    Integer.parseInt(resultSet.getString(6))
            ));
        }
        return list;
    }

    public static ArrayList<Batch> getBatches() throws SQLException, ClassNotFoundException {
        ArrayList<Batch> list = new ArrayList<>();
        ResultSet resultSet = CrudUtil.execute("SELECT batchId FROM batch ");
        while (resultSet.next()) {
            list.add(
                    new Batch(
                            resultSet.getString(1)
                    ));
        }
        return list;
    }
}
