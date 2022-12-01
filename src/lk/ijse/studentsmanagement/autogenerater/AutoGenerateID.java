package lk.ijse.studentsmanagement.autogenerater;
import javafx.scene.control.Label;
import lk.ijse.studentsmanagement.model.*;
import lk.ijse.studentsmanagement.to.*;

import java.sql.SQLException;
public class AutoGenerateID {
    public static void setLblPaymentID(Label lblPaymentID) throws SQLException, ClassNotFoundException {
        String lastTestPaymentID = TestPaymentModel.getLastPaymentID();
        if (lastTestPaymentID == null) {
            lblPaymentID.setText("T00001");
        } else {
            String[] split = lastTestPaymentID.split("[T]");
            int lastDigits = Integer.parseInt(split[1]);
            lastDigits++;
            lblPaymentID.setText(String.format("T%05d", lastDigits));
        }
    }
    public static void setLblRegPaymentID(Label lblPaymentID) throws SQLException, ClassNotFoundException {
        String lastTestPaymentID = PaymentModel.getLastPaymentID();
        if (lastTestPaymentID == null) {
            lblPaymentID.setText("P000001");
        } else {
            String[] split = lastTestPaymentID.split("[P]");
            int lastDigits = Integer.parseInt(split[1]);
            lastDigits++;
            lblPaymentID.setText(String.format("P%06d", lastDigits));
        }
    }
    public static void setRegistrationID(Label lblRegistrationID) throws SQLException, ClassNotFoundException {
        String lastRegistrationId = RegistrationModel.getLastRegistrationID();
        if (lastRegistrationId == null) {
            lblRegistrationID.setText("IT000001");
        } else {
            String[] split = lastRegistrationId.split("[I][T]");
            int lastDigits = Integer.parseInt(split[1]);
            lastDigits++;
            lblRegistrationID.setText(String.format("IT%06d", lastDigits));
        }
    }
    public static void setBatchNo(Label lblBatchNo,String course) throws SQLException, ClassNotFoundException {
        Batch batch = BatchModel.getLastBatchNo(course);
        if (batch == null) {
            lblBatchNo.setText(String.valueOf(1));
        } else {
            int batchNo = batch.getBatchNo();
            batchNo++;
            lblBatchNo.setText(String.valueOf(batchNo));
        }
    }

    public static void loadExamID(Label lblExamId) throws SQLException, ClassNotFoundException {
        Exam lastExamID = ExamModel.getExamID();
        if(lastExamID==null){
            lblExamId.setText("EX00001");
        }else{
            String id = lastExamID.getBatchId();
            String[] split = id.split("[E][X]");
            int lastDigits = Integer.parseInt(split[1]);
            lastDigits++;
            lblExamId.setText(String.format("EX%05d", lastDigits));
        }
    }

    public static void loadIQTestIDS(Label lblExamID) throws SQLException, ClassNotFoundException {
        IQTest lastExamID = IQTestModel.getExamID();
        if(lastExamID==null){
            lblExamID.setText("IQ0001");
        }else{
            String id = lastExamID.getId();
            String[] split = id.split("[I][Q]");
            int lastDigits = Integer.parseInt(split[1]);
            lastDigits++;
            lblExamID.setText(String.format("IQ%04d", lastDigits));
        }
    }

    public static void generateSubjectID(Label lblSubID) throws SQLException, ClassNotFoundException {
        Subject lastSubjectID = Subject.getLastSubjectID();
        if(lastSubjectID==null){
            lblSubID.setText("ITS0001");
        }else{
            String id = lastSubjectID.getId();
            String[] split = id.split("[I][T][S]");
            int lastDigits = Integer.parseInt(split[1]);
            lastDigits++;
            lblSubID.setText(String.format("ITS%04d", lastDigits));
        }
    }
}
