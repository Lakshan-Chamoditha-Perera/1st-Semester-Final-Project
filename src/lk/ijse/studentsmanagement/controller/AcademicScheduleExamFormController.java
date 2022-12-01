package lk.ijse.studentsmanagement.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import jdk.nashorn.internal.runtime.regexp.joni.Regex;
import lk.ijse.studentsmanagement.autogenerater.AutoGenerateID;
import lk.ijse.studentsmanagement.comboLoad.ComboLoader;
import lk.ijse.studentsmanagement.comboLoad.TableLoader;
import lk.ijse.studentsmanagement.model.BatchModel;
import lk.ijse.studentsmanagement.model.CourseSubjectDetailModel;
import lk.ijse.studentsmanagement.model.ExamModel;
import lk.ijse.studentsmanagement.model.SubjectModel;
import lk.ijse.studentsmanagement.regex.RegExPatterns;
import lk.ijse.studentsmanagement.to.Batch;
import lk.ijse.studentsmanagement.to.CourseSubjectDetail;
import lk.ijse.studentsmanagement.to.Exam;
import lk.ijse.studentsmanagement.to.Subject;
import lk.ijse.studentsmanagement.util.Navigation;
import lk.ijse.studentsmanagement.util.Routes;

import java.io.IOException;
import java.net.FileNameMap;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static sun.net.www.MimeTable.loadTable;

public class AcademicScheduleExamFormController implements Initializable {

    public Label lblExamId;
    public TableColumn colExamID;
    public TableView tblExam;
    public TableColumn colSubjectID;
    public TableColumn colBatchId;
    public TableColumn colDescription;
    public TableColumn colDate;
    public TableColumn colLab;
    public TableColumn colTime;
    public JFXButton btnScheduleExam;
    public JFXButton btnCancel;
    public ComboBox<String> cmbBatchID;
    public ComboBox<String> cmbSubjectID;
    public Label lblSubjectName;
    public JFXTimePicker cmbTime;
    public Label lblSelectBatch;
    public Label lblSelectSubject;
    public Label lblSelectDescription;
    public Label lblPickDate;
    public Label lblPickTime;
    public Label lblEnterLab;
    @FXML
    private AnchorPane pane;

    @FXML
    private JFXTextField txtDescription;

    @FXML
    private JFXDatePicker cmbDate;

    @FXML
    private JFXTextField txtLab;

    @FXML
    void backClickOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.EXAMS, pane);
    }

    @FXML
    void btnCancelOnAction(ActionEvent event) {

    }

    @FXML
    void btnSheduleOnAction(ActionEvent event) {
        try {
            if (cmbBatchID.getValue() != null) {
                if (cmbSubjectID.getValue() != null) {
                    if (RegExPatterns.getNamePattern().matcher(txtDescription.getText()).matches()) {
                        if (cmbDate.getValue()!= null) {
                            if (cmbTime.getValue() != null) {
                                if (txtLab.getText() != null) {
                                    boolean isAdded = ExamModel.addExam(
                                            new Exam(
                                                    lblExamId.getText(),
                                                    cmbSubjectID.getValue(),
                                                    cmbBatchID.getValue(),
                                                    txtDescription.getText(),
                                                    Date.valueOf(cmbDate.getValue()),
                                                    txtLab.getText(),
                                                    Time.valueOf(cmbTime.getValue())
                                            )
                                    );
                                    if (isAdded) {
                                        new Alert(Alert.AlertType.INFORMATION, "Done").show();
                                        Navigation.navigate(Routes.SCHEDULE_EXAMS, pane);
                                    } else {
                                        new Alert(Alert.AlertType.ERROR, "ERROR").show();
                                    }

                                } else {
                                    new Alert(Alert.AlertType.INFORMATION, "Select Lab").show();
                                    lblEnterLab.setVisible(true);
                                    txtLab.setFocusColor(Color.RED);
                                }
                            } else {
                                new Alert(Alert.AlertType.INFORMATION, "Select Time").show();
                                lblPickTime.setVisible(true);
                            }
                        } else {

                            new Alert(Alert.AlertType.INFORMATION, "Select Date").show();
                            lblPickDate.setVisible(true);
                        }
                    } else {
                        txtDescription.setFocusColor(Color.RED);
                        lblSelectDescription.setVisible(true);
                    }
                } else {
                    new Alert(Alert.AlertType.INFORMATION, "Select Subject").show();
                    lblSelectSubject.setVisible(true);
                }
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Select batch first !").show();
                lblSelectBatch.setVisible(true);
            }
        } catch (SQLException | ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }

    }

    public void cmbBatchIdOnAction(ActionEvent actionEvent) {
        try {
            lblSubjectName.setText(null);
            Batch batch = BatchModel.getCourseID(cmbBatchID.getValue());
            if (batch != null) {
                ComboLoader.loadBatchCourseSubjectID(cmbSubjectID, batch.getId());
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        colExamID.setCellValueFactory(new PropertyValueFactory<>("examId"));
        colSubjectID.setCellValueFactory(new PropertyValueFactory<>("subjectId"));
        colBatchId.setCellValueFactory(new PropertyValueFactory<>("batchId"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("examDate"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("lab"));
        colLab.setCellValueFactory(new PropertyValueFactory<>("time"));

        try {
            AutoGenerateID.loadExamID(lblExamId);
            boolean loadBatchIDS = ComboLoader.loadBatchIDS(cmbBatchID);
            if(!loadBatchIDS){
                new Alert(Alert.AlertType.INFORMATION,"No any batcehs added").show();
            }
            boolean isExamLoaded = TableLoader.loadAllExams(tblExam);
            if(!isExamLoaded){
                new Alert(Alert.AlertType.INFORMATION, "No any exam loaded").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, String.valueOf(e)).show();
        }
    }

    public void cmbSubjectOnAction(ActionEvent actionEvent) {
        try {
            if (cmbSubjectID.getValue() != null) {
                String name = SubjectModel.getSubjectName(new Subject(cmbSubjectID.getValue()));
                if (name != null) {
                    lblSubjectName.setText(name);
                    loadExamTable(cmbBatchID.getValue().toString(), cmbSubjectID.getValue().toString());
                } else {
                    new Alert(Alert.AlertType.ERROR, "Can not find subject name").show();
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "ERROR").show();
        }
    }

    private void loadExamTable(String batchId, String subjectId) throws SQLException, ClassNotFoundException {
        if (batchId != null | subjectId != null) {
            TableLoader.loadExamBatchSubjectTable(tblExam, batchId, subjectId);
        }
    }


    public void cmbTimeOnMouseClicked(MouseEvent mouseEvent) {
        lblPickTime.setVisible(false);
    }

    public void txtLabOnMouseClicked(MouseEvent mouseEvent) {
        lblEnterLab.setVisible(false);
    }

    public void cmbDateOnMouseClicked(MouseEvent mouseEvent) {
        lblPickDate.setVisible(false);
    }

    public void txtDescriptionOnMouseClicked(MouseEvent mouseEvent) {
        lblSelectDescription.setVisible(false);
    }

    public void cmbSubjectOnMouseClicked(MouseEvent mouseEvent) {
        lblSelectSubject.setVisible(false);
    }

    public void cmbBatchOnMouseClicked(MouseEvent mouseEvent) {
        lblSelectBatch.setVisible(false);
    }
}
