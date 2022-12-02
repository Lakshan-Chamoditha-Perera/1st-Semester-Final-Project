package lk.ijse.studentsmanagement.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.studentsmanagement.model.InquiryModel;
import lk.ijse.studentsmanagement.tblModels.InquiryTM;
import lk.ijse.studentsmanagement.to.Inquiry;
import lk.ijse.studentsmanagement.util.Navigation;
import lk.ijse.studentsmanagement.util.Routes;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.ResourceBundle;

public class InquiriesFormController implements Initializable {
    public AnchorPane pane;
    public TableView tblInquiries;
    public TableColumn colId;
    public JFXTextField txtId;
    public TableColumn colStatus;
    public TableColumn colDate;
    public TableColumn colGender;
    public TableColumn colEmail;
    public TableColumn colMobileNumber;
    public TableColumn colCity;
    public TableColumn colName;
    public TableColumn colButton;

    public void btnAddStdOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADD_STUDENT,pane);
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {

    }

    public void btnViewStdOnAction(ActionEvent actionEvent) throws IOException {Navigation.navigate(Routes.VIEW_STUDENT,pane);}

    public void btnUpdateStdOnAction(ActionEvent actionEvent) throws IOException {Navigation.navigate(Routes.UPDATE_STUDENT,pane);}

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colId.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colMobileNumber.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        try {
            addToTable();
        } catch (SQLException | ClassNotFoundException e) {
          new Alert(Alert.AlertType.ERROR,String.valueOf(e)).show();
        }

    }

    private void addToTable() throws SQLException, ClassNotFoundException {

        ArrayList<Inquiry> allInquires = InquiryModel.getAllInquires();

        ObservableList<InquiryTM> inquiryTMObservableList = FXCollections.observableArrayList();

        for (Inquiry ele: allInquires) {
            inquiryTMObservableList.add(new InquiryTM(
                    ele.getStudentID(),
                    ele.getName(),
                    ele.getCity(),
                    ele.getEmail(),
                    ele.getMobile(),
                    ele.getDate(),
                    ele.getGender(),
                    ele.getStatus()
            ));
        }
        tblInquiries.setItems(inquiryTMObservableList);
    }
}