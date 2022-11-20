package lk.ijse.studentsmanagement.to;

public class IQTest {
    String id;
    String date;
    String time;
    String lab;
    double amount;

    public IQTest(String id, String date, String time, String lab,  double amount) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.lab = lab;
        this.amount= amount;
    }

    public double getAmount() {
        return amount;
    }

    public String getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getLab() {
        return lab;
    }
}
