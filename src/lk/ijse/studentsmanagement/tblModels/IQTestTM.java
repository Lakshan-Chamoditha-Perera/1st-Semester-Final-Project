package lk.ijse.studentsmanagement.tblModels;

import lk.ijse.studentsmanagement.to.IQTest;

import java.sql.Date;
import java.sql.Time;

public class IQTestTM  {
    public IQTestTM(String id, Date date, Time time, String lab, double amount) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.lab = lab;
        this.amount = amount;
    }

    String id;
    Date date;
    Time time;
    String lab;
    double amount;

    public String getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public Time getTime() {
        return time;
    }

    public String getLab() {
        return lab;
    }

    public double getAmount() {
        return amount;
    }
}
