package lk.ijse.studentsmanagement.comboLoad;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import lk.ijse.studentsmanagement.model.IQTestModel;
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



}
