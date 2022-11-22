package lk.ijse.studentsmanagement.to;

import java.sql.Date;

public class Batch {
    String id;
    int batchNo;
    String courseId;
    double fee;
    Date starting_date;
    int maxStdCount;

    public Batch(String id, int batchNo, String courseId, double fee, Date starting_date, int maxStdCount) {
        this.id = id;
        this.batchNo = batchNo;
        this.courseId = courseId;
        this.fee = fee;
        this.starting_date = starting_date;
        this.maxStdCount = maxStdCount;
    }

    public Batch(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public int getBatchNo() {
        return batchNo;
    }

    public String getCourseId() {
        return courseId;
    }

    public double getFee() {
        return fee;
    }

    public Date getStarting_date() {
        return starting_date;
    }

    public int getMaxStdCount() {
        return maxStdCount;
    }

    @Override
    public String toString() {
        return "Batch{" +
                "id='" + id + '\'' +
                ", batchNo=" + batchNo +
                ", courseId='" + courseId + '\'' +
                ", fee=" + fee +
                ", starting_date=" + starting_date +
                ", maxStdCount=" + maxStdCount +
                '}';
    }
}
