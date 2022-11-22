package lk.ijse.studentsmanagement.controller;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.studentsmanagement.comboLoad.TableLoader;
import lk.ijse.studentsmanagement.model.BatchModel;
import lk.ijse.studentsmanagement.model.CourseModel;
import lk.ijse.studentsmanagement.to.Batch;
import lk.ijse.studentsmanagement.to.Course;
import lk.ijse.studentsmanagement.util.Navigation;
import lk.ijse.studentsmanagement.util.Routes;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static lk.ijse.studentsmanagement.comboLoad.ComboLoader.loadCoursesList;

public class AcademicAddNewBatchFormController implements Initializable {

    public AnchorPane pane;
    public Label lblId;
    public Label lblName;
    public Label lblDuration;
    public JFXDatePicker cmbDate;
    public ComboBox cmbCourseID;
    public JFXTextField txtBatchNo;
    public JFXTextField txtCrowd;
    public JFXTextField txtCourseFee;
    public TableView tblOnGoingBatches;
    public TableColumn colBatchID;
    public TableColumn colFee;
    public TableColumn colCrowd;
    public TableColumn colStartDate;
    public Label lblBatchNo;
    public TableColumn colBatchNo;
    @FXML
    private ComboBox<String> cmbCourse;

    @FXML
    void btnAddOnAction(ActionEvent event) throws IOException {
    }


    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.ACADEMIC_MANAGE_BATCHES,pane);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            loadCoursesList(cmbCourse);
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, String.valueOf(e));
        }
        colBatchID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colFee.setCellValueFactory(new PropertyValueFactory<>("fee"));
        colStartDate.setCellValueFactory(new PropertyValueFactory<>("starting_date"));
        colCrowd.setCellValueFactory(new PropertyValueFactory<>("maxStdCount"));
        colBatchNo.setCellValueFactory(new PropertyValueFactory<>("batchNo"));

    }

    public void cmbCourseOnAction(ActionEvent actionEvent) {
        Course courseDetail = null;
        try {
            courseDetail = CourseModel.getCourseDetail(cmbCourse.getValue());
            lblName.setText(courseDetail.getName());
            lblDuration.setText(courseDetail.getDuration());
            lblId.setText(courseDetail.getId());
            TableLoader.setBatchTable(tblOnGoingBatches, cmbCourse.getValue());
        }catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, String.valueOf(e));
        }
    }
}
