package lk.ijse.studentsmanagement.comboLoad;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.studentsmanagement.model.BatchModel;
import lk.ijse.studentsmanagement.model.RegistrationModel;
import lk.ijse.studentsmanagement.tblModels.BatchTM;
import lk.ijse.studentsmanagement.tblModels.RegistrationTM;
import lk.ijse.studentsmanagement.to.Batch;
import lk.ijse.studentsmanagement.to.Registration;

import java.sql.SQLException;
import java.util.ArrayList;

public class TableLoader {
    public static  void loadTableCourseBatch(TableView tblStd, String batch, String course) throws SQLException, ClassNotFoundException {

        //load students
        ArrayList <Registration> registrationsArrayList = RegistrationModel.getCourseBatchList(course,batch);
        ObservableList<RegistrationTM> observableArrayList = FXCollections.observableArrayList();
        int count = 0;
        for (Registration ele: registrationsArrayList) {
            observableArrayList.add(
                    new RegistrationTM(
                            ele.getRegistrationId(),
                            ele.getName(),
                            ele.getMobile(),
                            ele.getEmail(),
                            ele.getStatus()
                    )
            );
        }
        tblStd.setItems(observableArrayList);
    }
    public static void setBatchTable(TableView tblBatches, String course) throws SQLException, ClassNotFoundException {
        //load batch table in specific course
        ArrayList <Batch> batchesArrayList = BatchModel.getBatches(course);
        ObservableList<BatchTM> observableListBatchTM = FXCollections.observableArrayList();
       int count = 0;
        for (Batch ele: batchesArrayList) {
            System.out.println(ele);
            observableListBatchTM.add(
                    new BatchTM(
                            ele.getId(),
                            ele.getBatchNo(),
                            ele.getFee(),
                            ele.getStarting_date(),
                            ele.getMaxStdCount()
                    )

            );
            System.out.println(observableListBatchTM.get(count++));
        }
        tblBatches.setItems(observableListBatchTM);
    }


    public static void setCellValues(TableColumn[] columns) {
        columns[0].setCellValueFactory(new PropertyValueFactory<>("registrationId"));
        columns[1].setCellValueFactory(new PropertyValueFactory<>("name"));
        columns[2].setCellValueFactory(new PropertyValueFactory<>("mobile"));
        columns[3].setCellValueFactory(new PropertyValueFactory<>("email"));
        columns[4].setCellValueFactory(new PropertyValueFactory<>("status"));
//
        columns[5].setCellValueFactory(new PropertyValueFactory<>("id"));
        columns[6].setCellValueFactory(new PropertyValueFactory<>("batchNo"));
        columns[7].setCellValueFactory(new PropertyValueFactory<>("fee"));
        columns[8].setCellValueFactory(new PropertyValueFactory<>("starting_date"));
        columns[9].setCellValueFactory(new PropertyValueFactory<>("maxStdCount"));
    }

}
