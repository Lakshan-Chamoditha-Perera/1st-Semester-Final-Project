package lk.ijse.studentsmanagement.to;

public class StudentInquiry {
    private String studentID;
    private String name ;
    private String city;
    private String email;
    private String mobile;
    private String date ;
    private String gender;
    private String status ;

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "StudentInquiry{" +
                "studentID='" + studentID + '\'' +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", date='" + date + '\'' +
                ", gender='" + gender + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public StudentInquiry(String studentID, String name, String city, String email, String mobile, String date, String gender, String status) {
        this.studentID = studentID;
        this.name = name;
        this.city = city;
        this.email = email;
        this.mobile = mobile;
        this.date = date;
        this.gender = gender;
        this.status = status;
    }
}