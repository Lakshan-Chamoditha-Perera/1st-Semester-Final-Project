package lk.ijse.studentsmanagement.to;

public class TestPayment {
    String id;
    String studentID;
    String date;
    String remark = "test payment";
    double amount;
    String iqTestId;

    public String getIqTestId() {
        return iqTestId;
    }

    InquiryIQTestDetail inquiryIQTestDetail;

    public TestPayment(String id, String studentID, String date, String remark, double amount, String iqTestId) {
        this.id = id;
        this.studentID = studentID;
        this.date = date;
        this.remark = remark;
        this.amount = amount;
        this.iqTestId = iqTestId;
    }

    public InquiryIQTestDetail getInquiryIQTestDetail() {
        return inquiryIQTestDetail;
    }

    public TestPayment(String id, String studentID, String date, String remark, double amount, String iqTestId, InquiryIQTestDetail inquiryIQTestDetail) {
        this.id = id;
        this.studentID = studentID;
        this.date = date;
        this.remark = remark;
        this.amount = amount;
        this.iqTestId = iqTestId;
        this.inquiryIQTestDetail = inquiryIQTestDetail;
    }

    public String getId() {
        return id;
    }

    public String getStudentID() {
        return studentID;
    }

    public String getDate() {
        return date;
    }

    public String getRemark() {
        return remark;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "TestPayment{" +
                "id='" + id + '\'' +
                ", studentID='" + studentID + '\'' +
                ", date='" + date + '\'' +
                ", remark='" + remark + '\'' +
                ", amount=" + amount +
                ", inquiryIQTestDetail=" + inquiryIQTestDetail +
                '}';
    }
}
