package lk.ijse.studentsmanagement.controller;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import lk.ijse.studentsmanagement.autogenerater.AutoGenerateID;
import lk.ijse.studentsmanagement.comboLoad.TableLoader;
import lk.ijse.studentsmanagement.model.BatchModel;
import lk.ijse.studentsmanagement.model.CourseModel;
import lk.ijse.studentsmanagement.regex.RegExPatterns;
import lk.ijse.studentsmanagement.to.Batch;
import lk.ijse.studentsmanagement.to.Course;
import lk.ijse.studentsmanagement.util.Navigation;
import lk.ijse.studentsmanagement.util.Routes;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
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
    public Button btnAdd;
    @FXML
    private ComboBox<String> cmbCourse;

    @FXML
    void btnAddOnAction(ActionEvent event) {
        try {
            boolean isAdded = addNewBatch();
            String text = (isAdded) ? "Success" : "Error";
            if(isAdded){
                new Alert(Alert.AlertType.INFORMATION, text).show();
                Navigation.navigate(Routes.ACADEMIC_ADD_NEW_BATCH, pane);
            }else{
                new Alert(Alert.AlertType.INFORMATION, text).show();
            }
        } catch (SQLException | ClassNotFoundException | IOException e) {
            new Alert(Alert.AlertType.ERROR, String.valueOf(e)).show();
        }
    }

    private boolean addNewBatch() throws SQLException, ClassNotFoundException {
        if (!cmbCourse.getValue().isEmpty()) {
            if (RegExPatterns.getDoublePattern().matcher(txtCourseFee.getText()).matches()) {
                if (cmbDate.getValue() != null) {
                    if (Integer.parseInt(txtCrowd.getText())>0) {
                        return BatchModel.addNewBatch(
                                new Batch(
                                        lblBatchNo.getText() + cmbCourse.getValue(),
                                        Integer.parseInt(lblBatchNo.getText()),
                                        cmbCourse.getValue().toString(),
                                        Double.parseDouble(txtCourseFee.getText()),
                                        Date.valueOf(cmbDate.getValue()),
                                        Integer.parseInt(txtCrowd.getText())
                                )
                        );
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Enter Crowd!").show();
                    }
                } else {
                    new Alert(Alert.AlertType.ERROR, "Select a date first!").show();
                }
            } else {
                txtCourseFee.setFocusColor(Color.RED);
                new Alert(Alert.AlertType.ERROR, "Enter Fee Correctly").show();
            }
        } else {
            new Alert(Alert.AlertType.INFORMATION, "Please Select A Course First!").show();
        }
        return false;
    }

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.ACADEMIC_MANAGE_BATCHES, pane);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnAdd.setDisable(true);
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
        btnAdd.setDisable(false);
        Course courseDetail = null;
        try {
            courseDetail = CourseModel.getCourseDetail(cmbCourse.getValue());
            lblName.setText(courseDetail.getName());
            lblDuration.setText(courseDetail.getDuration());
            lblId.setText(courseDetail.getId());
            TableLoader.setBatchTable(tblOnGoingBatches, cmbCourse.getValue());
            AutoGenerateID.setBatchNo(lblBatchNo, cmbCourse.getValue());
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, String.valueOf(e)).show();
        }
    }
}
