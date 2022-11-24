package lk.ijse.studentsmanagement.to;

import java.sql.Date;
import java.sql.Time;

public class Exam {
    String examId;
    String subjectId;
    String batchId;
    String description;
    Date examDate;
    String lab;
    Time time;

    public Exam(String examId) {
        this.examId = examId;
    }


    @Override
    public String toString() {
        return "Exam{" +
                "examId='" + examId + '\'' +
                ", subjectId='" + subjectId + '\'' +
                ", batchId='" + batchId + '\'' +
                ", description='" + description + '\'' +
                ", examDate=" + examDate +
                ", lab='" + lab + '\'' +
                ", time=" + time +
                '}';
    }

    public String getExamId() {
        return examId;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public String getBatchId() {
        return batchId;
    }

    public String getDescription() {
        return description;
    }

    public Date getExamDate() {
        return examDate;
    }

    public String getLab() {
        return lab;
    }

    public Time getTime() {
        return time;
    }

    public Exam(String examId, String subjectId, String batchId, String description, Date examDate, String lab, Time time) {
        this.examId = examId;
        this.subjectId = subjectId;
        this.batchId = batchId;
        this.description = description;
        this.examDate = examDate;
        this.lab = lab;
        this.time = time;
    }
}
