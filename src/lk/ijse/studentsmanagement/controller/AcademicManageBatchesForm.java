package lk.ijse.studentsmanagement.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import lk.ijse.studentsmanagement.util.Navigation;
import lk.ijse.studentsmanagement.util.Routes;

import java.io.IOException;

public class AcademicManageBatchesForm {

    public TableView tableBatches;
    public TableColumn colBatchID;
    public TableColumn colBatchNo;
    public TableColumn colCourseId;
    public TableColumn colFee;
    public TableColumn colDate;
    public TableColumn colMaxCount;
    @FXML
    private AnchorPane pane;

    @FXML
    void btnAddNewBatchOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.ACADEMIC_ADD_NEW_BATCH,pane);
    }

    @FXML
    void btnViewBatchesOnAction(ActionEvent event) {

    }

}
