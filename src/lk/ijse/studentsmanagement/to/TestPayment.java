package lk.ijse.studentsmanagement.to;

public class TestPayment {
    String id;
    String studentID;
    String date;
    String remark = "test payment";
    double amount;

    InquiryIQTestDetail inquiryIQTestDetail;

    public InquiryIQTestDetail getInquiryIQTestDetail() {
        return inquiryIQTestDetail;
    }

    public TestPayment(String id, String studentID, String date, double amount, InquiryIQTestDetail inquiryIQTestDetail) {
        this.id = id;
        this.studentID = studentID;
        this.date = date;
        this.amount = amount;
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
