package lk.ijse.studentsmanagement.controller;

import com.jfoenix.controls.JFXRadioButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.studentsmanagement.comboLoad.ComboLoader;
import lk.ijse.studentsmanagement.comboLoad.TableLoader;
import lk.ijse.studentsmanagement.model.ExamModel;
import lk.ijse.studentsmanagement.model.IQTestModel;
import lk.ijse.studentsmanagement.model.InquiryIQTestDetailModel;
import lk.ijse.studentsmanagement.tblModels.IQTestTM;
import lk.ijse.studentsmanagement.tblModels.InquiryIQTestDetailTM;
import lk.ijse.studentsmanagement.to.Exam;
import lk.ijse.studentsmanagement.to.IQTest;
import lk.ijse.studentsmanagement.to.InquiryIQTestDetail;
import lk.ijse.studentsmanagement.util.Navigation;
import lk.ijse.studentsmanagement.util.Routes;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AcademicAddIQtestMarksFormController implements Initializable {

    public Label lblStdName;
    public Label lblStdID;
    public Button btnAdd;
    public Label txtExamTime;
    public Label txtLab;
    public Label txtExamDate;
    public AnchorPane tblPane;
    @FXML
    private AnchorPane pane;

    @FXML
    private ComboBox<String> cmbExamID;

    @FXML
    private TableView<InquiryIQTestDetailTM> tblStdList;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colExamId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colResult;

    @FXML
    private JFXRadioButton rbtnPass;

    @FXML
    private ToggleGroup results;

    @FXML
    private JFXRadioButton rbtnFail;

    @FXML
    void btnAddOnAction(ActionEvent event) {
        try {
            String result = (rbtnPass.isSelected()) ? "Pass" : "Fail";
            boolean isUpdated = InquiryIQTestDetailModel.updateInquiryIQTestDetail(
                    new InquiryIQTestDetail(
                            tblStdList.getSelectionModel().getSelectedItem().getStudentId(),
                            tblStdList.getSelectionModel().getSelectedItem().getTestId(),
                            result
                    )
            );
            System.out.println(isUpdated);
            if (isUpdated) {
                new Alert(Alert.AlertType.INFORMATION, "Done").show();
                loadTableResults();
            } else {
                new Alert(Alert.AlertType.ERROR, "Error").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.EXAMS, pane);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tblPane.setDisable(true);
        cmbExamID.setDisable(true);

        colId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colExamId.setCellValueFactory(new PropertyValueFactory<>("testId"));
        colResult.setCellValueFactory(new PropertyValueFactory<>("result"));

        try {
            boolean isIQExamIDLoaded = ComboLoader.loadIQExamIDComboBox(cmbExamID);
            if (!isIQExamIDLoaded) {
                new Alert(Alert.AlertType.INFORMATION, "No any exam Added").show();
            } else {
                cmbExamID.setDisable(false);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadTableResults() throws SQLException, ClassNotFoundException {
        lblStdID.setText("");
        lblStdName.setText("");
        btnAdd.setDisable(true);

        boolean isLoaded = TableLoader.loadInquiryIQTestResults(tblStdList, new IQTest(cmbExamID.getValue()));
        if (!isLoaded) {
            new Alert(Alert.AlertType.INFORMATION, "Empty list").show();
        }
    }

    public void cmbExamIDOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (!cmbExamID.getValue().isEmpty()) {
            loadTableResults();
           IQTest examDetails= IQTestModel.getExamDetails(new IQTest(cmbExamID.getValue()));
            if(examDetails!=null){
                txtExamDate.setText(examDetails.getDate().toString());
                txtLab.setText(examDetails.getLab());
                txtExamTime.setText(examDetails.getTime().toString());
            }

            tblPane.setDisable(false);
        }
    }

    public void tblStdListOnAction(MouseEvent mouseEvent) {
        lblStdID.setText("");
        lblStdName.setText("");
        if (tblStdList.getSelectionModel().getSelectedItem() != null) {
            lblStdID.setText(tblStdList.getSelectionModel().getSelectedItem().getStudentId());
            lblStdName.setText(tblStdList.getSelectionModel().getSelectedItem().getName());
            btnAdd.setDisable(false);
        } else {
            btnAdd.setDisable(true);
        }
    }
}
