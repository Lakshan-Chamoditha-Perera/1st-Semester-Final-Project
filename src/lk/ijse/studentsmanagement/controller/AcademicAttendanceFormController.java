package lk.ijse.studentsmanagement.controller;

import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import lk.ijse.studentsmanagement.comboLoad.TableLoader;
import lk.ijse.studentsmanagement.model.AttendanceModel;
import lk.ijse.studentsmanagement.qr.QRScanner;
import lk.ijse.studentsmanagement.regex.RegExPatterns;
import lk.ijse.studentsmanagement.tblModels.AttendanceTM;
import lk.ijse.studentsmanagement.to.Attendance;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AcademicAttendanceFormController implements Initializable {

    public static String scan;
   static QRScanner qrScanner;
    public TableColumn colBatch;
    @FXML
    private TableView<AttendanceTM> tblAttendance;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colStatus;

    @FXML
    private TableColumn<?, ?> colStatus1;

    @FXML
    private TextField txtRegistrationId;

    @FXML
    private Button btnMark;

    @FXML
    void btnMarkOnAction() throws InterruptedException {

    }


    public static void remove() {
        System.out.println("Close");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        colId.setCellValueFactory(new PropertyValueFactory<>("registrationId"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colBatch.setCellValueFactory(new PropertyValueFactory<>("batchID"));

        if(qrScanner==null){
            qrScanner = new QRScanner();
        }

        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(qrScanner.getVideoPanel());
        Stage stage = new Stage();


        stage.setScene(new Scene(stackPane, 800, 600));
        stage.show();
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                QRScanner.webcam.close();
                qrScanner.thread.stop();
            }
        });

        try {
            boolean isLoaded = loadAttendanceTable();
            if (!isLoaded) {
                new Alert(Alert.AlertType.INFORMATION, "No any Attendance yet!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
    }

    private boolean loadAttendanceTable() throws SQLException, ClassNotFoundException {
        return TableLoader.loadMarkAttendanceTable(Date.valueOf(LocalDate.now()), tblAttendance);
    }
}
