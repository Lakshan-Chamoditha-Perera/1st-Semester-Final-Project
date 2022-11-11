package lk.ijse.studentsmanagement.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import lk.ijse.studentsmanagement.util.Navigation;
import lk.ijse.studentsmanagement.util.Routes;

import java.io.IOException;

public class InquiriesFormController {
    public AnchorPane pane;
    public TableView tblInquiries;
    public JFXTextField txtID;

    public void btnAddStdOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADD_STUDENT,pane);
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
    }

    public void btnViewStdOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.VIEW_STUDENT,pane);
    }

    public void btnUpdateStdOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.UPDATE_STUDENT,pane);
    }
}
