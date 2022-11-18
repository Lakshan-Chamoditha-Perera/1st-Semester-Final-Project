package lk.ijse.studentsmanagement.comboLoad;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import lk.ijse.studentsmanagement.model.IQTestModel;
import lk.ijse.studentsmanagement.to.IQTest;
import lk.ijse.studentsmanagement.util.ComboLoad;
import java.sql.SQLException;
import java.util.ArrayList;

public class ComboLoader {

    public static void loadComboBox(ComboBox <String> comboBox, ComboLoad path) throws SQLException, ClassNotFoundException {
        switch (path){
            case IQTEST:
                ArrayList<IQTest> iqTestList = IQTestModel.getIQTestList();
                ObservableList<String> observableArrayList = FXCollections.observableArrayList();
                for (IQTest ele: iqTestList) {
                    observableArrayList.add(ele.getDate());
                }
                comboBox.setItems(observableArrayList);
                break;

           // case  : break;
        }

    }
}
