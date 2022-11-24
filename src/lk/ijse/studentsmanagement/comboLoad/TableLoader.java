package lk.ijse.studentsmanagement.comboLoad;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.studentsmanagement.model.*;
import lk.ijse.studentsmanagement.tblModels.*;
import lk.ijse.studentsmanagement.to.*;

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
    public static void loadAllBatches(TableView tableBatches) throws SQLException, ClassNotFoundException {
        ArrayList <Batch> list =  BatchModel.getAllBAtches();
        ObservableList<BatchTM> observableArrayList = FXCollections.observableArrayList();
        for (Batch ele: list) {
            observableArrayList.add(
              new BatchTM(
                      ele.getId(),
                      ele.getBatchNo(),
                      ele.getCourseId(),
                      ele.getFee(),
                      ele.getStarting_date(),
                      ele.getMaxStdCount()
              )
            );
        }
        tableBatches.setItems(observableArrayList);
    }
    public static void loadExamBatchSubjectTable(TableView tblExam, String batchId, String subjectId) throws SQLException, ClassNotFoundException {
       ArrayList<Exam> list = ExamModel.getExams(batchId,subjectId);
       ObservableList<ExamTM> observableArrayList= FXCollections.observableArrayList();
       for (Exam ele: list) {
            observableArrayList.add(
                    new ExamTM(
                            ele.getExamId(),
                            ele.getSubjectId(),
                            ele.getBatchId(),
                            ele.getDescription(),
                            ele.getExamDate(),
                            ele.getLab(),
                            ele.getTime()
                    )
            );
        }
       tblExam.setItems(observableArrayList);
    }
    public static void loadIQTests(TableView tblIqTest) throws SQLException, ClassNotFoundException {
        method(tblIqTest);
    }

    public static void method(TableView tblIqTest) throws SQLException, ClassNotFoundException {
        ArrayList<IQTest> list = IQTestModel.getIQTestList();
        ObservableList<IQTestTM> observableArrayList= FXCollections.observableArrayList();
        for (IQTest ele: list) {
            observableArrayList.add(
                    new IQTestTM(
                            ele.getId(),
                            ele.getDate(),
                            ele.getTime(),
                            ele.getLab(),
                            ele.getAmount()
                    )
            );
        }
        tblIqTest.setItems(observableArrayList);
    }

    public static void loadRegistrationPayments(TableView tbl, String registrationId) throws SQLException, ClassNotFoundException {
        ArrayList<Payment> payments = PaymentModel.getPayments(
                new Registration(registrationId)
        );
        if (payments != null) {
            ObservableList<PaymentsTM> observableList = FXCollections.observableArrayList();
            for (Payment ele: payments) {
                observableList.add(new PaymentsTM(
                        ele.getId(),
                        ele.getType(),
                        ele.getRemark(),
                        ele.getAmount(),
                        ele.getDate()
                ));
            }
            tbl.setItems(observableList);
        }else{
            new Alert(Alert.AlertType.ERROR,"Invalid ID").show();
        }
    }


    public static void loadAllExams(TableView<ExamTM> tblExams) throws SQLException, ClassNotFoundException {
        ArrayList<Exam> list = ExamModel.getExams();
        if(list!=null){
            ObservableList <ExamTM> observableArrayList = FXCollections.observableArrayList();
            for (Exam ele: list) {
                observableArrayList.add(
                        new ExamTM(
                                ele.getExamId(),
                                ele.getSubjectId(),
                                ele.getBatchId(),
                                ele.getDescription(),
                                ele.getExamDate(),
                                ele.getLab(),
                                ele.getTime()
                        )
                );
            }
            tblExams.setItems(observableArrayList);
        }else{
            new Alert(Alert.AlertType.INFORMATION,"Exams Not added yet").show();
        }

    }

    public static void loadSubjectTable(TableView<SubjectTM> tblSubjects) throws SQLException, ClassNotFoundException {
        ArrayList<Subject> list = SubjectModel.getSubjectList();
        if(list!=null){
            ObservableList<SubjectTM> observableList = FXCollections.observableArrayList();
            for (Subject ele:list) {
                observableList.add(
                        new SubjectTM(
                                ele.getId(),
                                ele.getName(),
                                ele.getHours()
                        )
                );
            }
            tblSubjects.setItems(observableList);
        }else{
            new Alert(Alert.AlertType.INFORMATION,"Subjects Not added yet").show();
        }
    }
}