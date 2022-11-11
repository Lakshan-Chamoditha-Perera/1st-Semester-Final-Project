package lk.ijse.studentsmanagement.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import lk.ijse.studentsmanagement.model.SystemUserModel;
import lk.ijse.studentsmanagement.to.SystemUser;
import lk.ijse.studentsmanagement.util.Navigation;
import lk.ijse.studentsmanagement.util.Routes;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class LoginPageController implements Initializable {
    public AnchorPane pane;
    public JFXTextField txtPassword;
    public JFXTextField txtUserName;

    public void btnClickOnAction(ActionEvent actionEvent) throws IOException {
        /*if (pattern[0].matcher(txtUserName.getText()).matches()){
            if(pattern[1].matcher(txtPassword.getText()).matches()){
                login();
            }else {
                txtPassword.setFocusColor(Color.valueOf("RED"));
                txtPassword.requestFocus();
            }
        }else{
            txtUserName.setFocusColor(Color.valueOf("RED"));
            txtUserName.requestFocus();
        }*/
        switch (txtUserName.getText()){
            case "ad":  new Alert(Alert.AlertType.INFORMATION,"not fount ui").show();break;
            case "co" : Navigation.navigate(Routes.COUNSELOR,pane); break;
            case "ac":Navigation.navigate(Routes.ACADEMIC,pane);break;
        }
    }

    private void login() throws IOException {
        try {
            SystemUser systemUser = SystemUserModel.search(new SystemUser(txtUserName.getText(), txtPassword.getText()));
            if (systemUser != null) {
                if (systemUser.getUserName().equals(txtUserName.getText())) {
                    if (systemUser.getPassword().equals(txtPassword.getText())) {
                        switch (systemUser.getUserName()) {
                            case "ad":
                                Navigation.navigate(Routes.ADMIN, pane);
                                break;
                            case "co":
                                Navigation.navigate(Routes.COUNSELOR, pane);
                                break;

                            case "ac":
                                Navigation.navigate(Routes.ACADEMIC, pane);
                                break;
                        }
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Invalid Password").show();
                    }
                } else {
                    new Alert(Alert.AlertType.ERROR, "Invalid Username").show();
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Invalid User").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
    }

    Pattern pattern[] = new Pattern[2];

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pattern[0] = Pattern.compile("^[a-z0-9A-Z]{4,}$");
        pattern[1] = Pattern.compile("^[0-9a-zA-Z]{3,}$");//password

    }
}
