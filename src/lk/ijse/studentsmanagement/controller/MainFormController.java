package lk.ijse.studentsmanagement.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.CubicCurve;
import lk.ijse.studentsmanagement.util.Navigation;
import lk.ijse.studentsmanagement.util.Routes;

import java.io.IOException;

public class MainFormController {

    public AnchorPane pane;

    public void btnStartClickOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.LOGIN, pane);
    }

    @FXML
    private CubicCurve curve;

    @FXML
    void rootOnMouseExisted(MouseEvent event) {
        curve.setControlX2(10);
        //curve.setControlY2(-10);

    }

    @FXML
    void rootOnMouseMoved(MouseEvent event) {
        curve.setControlX2(event.getX()-100);
        // System.out.println(event.getX());
    }
}
