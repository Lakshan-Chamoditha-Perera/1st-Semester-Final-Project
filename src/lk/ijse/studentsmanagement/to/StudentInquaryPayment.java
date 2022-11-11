package lk.ijse.studentsmanagement.to;

public class StudentInquaryPayment {
    String studentID;
    String date;
    String remark;

    @Override
    public String toString() {
        return "StudentInquaryPayment{" +
                "studentID='" + studentID + '\'' +
                ", date='" + date + '\'' +
                ", remark='" + remark + '\'' +
                ", amount=" + amount +
                '}';
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public StudentInquaryPayment(String studentID, String date, String remark, double amount) {
        this.studentID = studentID;
        this.date = date;
        this.remark = remark;
        this.amount = amount;
    }

    public StudentInquaryPayment(String studentID) {
        this.studentID = studentID;
    }

    double amount;
}
