package lk.ijse.studentsmanagement.comboLoad;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import lk.ijse.studentsmanagement.model.BatchModel;
import lk.ijse.studentsmanagement.model.CourseModel;
import lk.ijse.studentsmanagement.model.IQTestModel;
import lk.ijse.studentsmanagement.to.Batch;
import lk.ijse.studentsmanagement.to.Course;
import lk.ijse.studentsmanagement.to.IQTest;

import java.sql.SQLException;
import java.util.ArrayList;

public class ComboLoader {
    public static void loadIQExamDatesComboBox(ComboBox<String> comboBox) throws SQLException, ClassNotFoundException {
        ArrayList<IQTest> iqTestList = IQTestModel.getIQTestList();
        ObservableList<String> observableArrayList = FXCollections.observableArrayList();
        for (IQTest ele : iqTestList) {
            observableArrayList.add(ele.getDate());
        }
        comboBox.setItems(observableArrayList);
    }

    public static void loadIQExamIDComboBox(ComboBox<String> comboBox) throws SQLException, ClassNotFoundException {
        ArrayList<IQTest> iqTestList = IQTestModel.getIQTestList();
        ObservableList<String> observableArrayList = FXCollections.observableArrayList();
        for (IQTest ele : iqTestList) {
            observableArrayList.add(ele.getId());
        }
        comboBox.setItems(observableArrayList);
    }

    public static void loadCoursesList(ComboBox<String> comboBox) throws SQLException, ClassNotFoundException {
        ArrayList<Course> courseArrayList = CourseModel.getCourseList();
        ObservableList<String> observableArrayList = FXCollections.observableArrayList();
        for (Course ele: courseArrayList) {
            observableArrayList.add(ele.getId());
        }
        comboBox.setItems(observableArrayList);
    }

    public static void LoadBatchIDS(ComboBox<String> comboBox,String courseName) throws SQLException, ClassNotFoundException {
            ArrayList<Batch> list = BatchModel.getBatchIDList(courseName);
            ObservableList<String> observableList = FXCollections.observableArrayList();
        for (Batch ele: list) {
            observableList.add(ele.getId());
            System.out.println(ele.getId());
        }
        comboBox.setItems(observableList);
    }

    public static void loadBatchIDS(ComboBox<String> comboBox) throws SQLException, ClassNotFoundException {
        ArrayList<Batch> batches = BatchModel.getBatches();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        for (Batch ele: batches) {
            observableList.add(ele.getId());
            System.out.println(ele.getId());
        }
        comboBox.setItems(observableList);
    }


}