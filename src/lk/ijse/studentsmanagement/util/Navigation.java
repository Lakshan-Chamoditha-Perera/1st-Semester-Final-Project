package lk.ijse.studentsmanagement.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Navigation {
    private static AnchorPane pane;

    public static void navigate(Routes route, AnchorPane pane) throws IOException {
        Navigation.pane = pane;
        Navigation.pane.getChildren().clear();
        Stage window = (Stage) Navigation.pane.getScene().getWindow();

        switch (route) {

            case LOGIN:
                window.setTitle("LOGIN PAGE");
                initUI("LoginPage.fxml");
                break;

            case COUNSELOR:
                window.setTitle("Counselor Main PAGE");
                initUI("CounsilorDashboard.fxml");
                break;

            case INQUIRIES:
                window.setTitle("Inquiries ");
                initUI("InquariesForm.fxml");
                break;

            case ADD_STUDENT:
                window.setTitle("Inquiries > Add Inquiry");
                initUI("AddInquiryForm.fxml");
                break;

            case VIEW_STUDENT:
                window.setTitle("Inquiries > View Inquiry");
                initUI("ViewStudentDetailsForm.fxml");
                break;

            case UPDATE_STUDENT:
                window.setTitle("Inquiries > Update Inquiry");
                initUI("UpdateInquaryForm.fxml");
                break;

            case IQTest:
                window.setTitle("IQTests");
                initUI("IQTestsForm.fxml");
                break;

            case MAIL_SERVICE_COUNSELOR:
                window.setTitle("MAIL_SERVICE");
                initUI("MailsCounselorForm.fxml");
                break;

            case ENROLLMENTS:
                window.setTitle("Enrollments");
                initUI("EnrollmentsForm.fxml");
                break;

            case VIEW_REGISTRATION:
                window.setTitle("VIEW_REGISTRATION");
                initUI("ViewStudentEnrollmentForm.fxml");
                break;


            case PAYMENTS:
                window.setTitle("Payments");
                initUI("PaymentsForm.fxml");
                break;


            case COURSES:
                window.setTitle("Courses");
                initUI("CoursesForm.fxml");
                break;

            case GDSE:
                window.setTitle("GDSE");
                initUI("GdseForm.fxml");
                break;

            case CMJD:
                window.setTitle("CMJD");
                initUI("CmjdForm.fxml");
                break;

            case DEP:
                window.setTitle("DEP");
                initUI("DepForm.fxml");
                break;

            case RWAD:
                window.setTitle("RWAD");
                initUI("RwadForm.fxml");
                break;

            case SendMailToStudentfromCounsilor:
                window.setTitle("Send Mail To Student ");
                initUI("SendMailToStudentfromCounsilorForm.fxml");
                break;

            case SendMailToGroupfromCounsilor:
                window.setTitle("Send Mail  ");
                initUI("SendMailToGroupfromCounsilorForm.fxml");
                break;

            case ACADEMIC:
                window.setTitle("Academic");
                initUI("AcademicDashboardForm.fxml");
                break;

            case EXAMS:
                window.setTitle("Exams");
                initUI("AcademicExamForm.fxml");
                break;

            case SCHEDULE_EXAMS:
                window.setTitle("SCHEDULE_EXAMS");
                initUI("AcademicScheduleExamForm.fxml");
                break;

            case SCHEDULE_IQTEST:
                window.setTitle("SCHEDULE_IQTEST");
                initUI("AcademicSheduleIQTestForm.fxml");
                break;

            case ACADEMIC_ADDIQMARKS:
                window.setTitle("ACADEMIC_ADDIQMARKS");
                initUI("AcademicAddIQtestMarksForm.fxml");
                break;

            case ACADEMIC_STUDENTFORM:
                window.setTitle("ACADEMIC_STUDENTFORM");
                initUI("AcademicStudentsForm.fxml");
                break;


            case ACADEMIC_TRANSCRIPT:
                window.setTitle("ACADEMIC_TRANSCRIPT");
                initUI("AcademicTranscriptForm.fxml");
                break;

            case ACADEMIC_VIEW_RESULTS_BY_BATCH:
                window.setTitle("ACADEMIC_VIEW_RESULTS_BY_BATCH");
                initUI("AcademicViewResultsForm.fxml");
                break;

            case ACADEMIC_MANAGE_EXAMS:
                window.setTitle("ACADEMIC_MANAGE_EXAMS");
                initUI("AcademicManageExamsForm.fxml");
                break;

            default:
                new Alert(Alert.AlertType.ERROR, "UI Not Found!").show();
                break;

        }
    }

    public static void initUI(String location) throws IOException {
        Navigation.pane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(Navigation.class.getResource("/lk/ijse/studentsmanagement/view/" + location))));
    }
}
