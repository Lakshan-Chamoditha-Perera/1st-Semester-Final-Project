package lk.ijse.studentsmanagement.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.studentsmanagement.model.InquiryModel;
import lk.ijse.studentsmanagement.regex.RegExPatterns;
import lk.ijse.studentsmanagement.to.Inquiry;
import lk.ijse.studentsmanagement.util.Navigation;
import lk.ijse.studentsmanagement.util.Routes;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static com.jfoenix.svg.SVGGlyphLoader.clear;

public class ViewStudentDetailsFormController implements Initializable {

    public JFXTextField txtMobile;
    public JFXTextField txtGender;
    public JFXTextField txtDate;
    public JFXButton btnSearch;
    public JFXTextField txtID;
    public Label lblInvalidID;
    @FXML
    private AnchorPane pane;
    @FXML
    private JFXTextField txtProgram;
    @FXML
    private JFXTextField txtRegistraion;
    @FXML
    private JFXTextField txtId;
    @FXML
    private JFXTextField txtName;
    @FXML
    private JFXTextField txtEmail;
    @FXML
    private JFXTextField txtCity;

    @FXML
    void backClickOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.INQUIRIES, pane);
    }

    public void btnSearchOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if(txtID.getText() != null){

            if(RegExPatterns.getIdPattern().matcher(txtID.getText()).matches()){
                Inquiry inquiry = InquiryModel.searchInquiry(new Inquiry(txtID.getText()));
                String text = null;
                if(!inquiry.equals(null)){
                    text ="exists";
                    txtId.setText(inquiry.getStudentID());
                    txtName.setText(inquiry.getName());
                    txtDate.setText(inquiry.getDate());
                    txtCity.setText(inquiry.getCity());
                    txtEmail.setText(inquiry.getEmail());
                    txtGender.setText(inquiry.getGender());
                    txtMobile.setText(inquiry.getMobile());
                }else{
                    text ="Not exists";
                    clearAll();
                }
                new Alert(Alert.AlertType.CONFIRMATION,text).show();
            }else{
                lblInvalidID.setVisible(true);
            }
        }else{
            new Alert(Alert.AlertType.ERROR,"Enter ID number First").show();
        }
    }

    public void txtIDClickOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        lblInvalidID.setVisible(false);
        btnSearchOnAction(new ActionEvent());
    }

    public void txtIDonMouseClicked(MouseEvent mouseEvent) {
        lblInvalidID.setVisible(false);
        clearAll();
    }

    private void clearAll() {
        txtId.clear();
        txtDate.clear();
        txtCity.clear();
        txtEmail.clear();
        txtGender.clear();
        txtMobile.clear();
        txtName.clear();
        txtID.clear();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        lblInvalidID.setVisible(false);

    }


}
