package lk.ijse.studentsmanagement.to;

public class InquiryIQTestDetail {
    String studentId;
    String testId;
    String result;

    public InquiryIQTestDetail(String studentId, String testId, String result) {
        this.studentId = studentId;
        this.testId = testId;
        this.result = result;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getTestId() {
        return testId;
    }

    public String getResult() {
        return result;
    }

    @Override
    public String toString() {
        return "InquiryIQTestDetail{" +
                "studentId='" + studentId + '\'' +
                ", testId='" + testId + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}
