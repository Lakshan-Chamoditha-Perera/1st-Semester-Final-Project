package lk.ijse.studentsmanagement.to;

import java.sql.Date;

public class Registration {
    String registrationId;
    String nic;
    String batchId;
    String courseId;
    String gardianId;
    String name;
    String address;
    String city;
    String postalCode;
    String mobile;
    String email;
    Date dob;

    String gender;
    String school;
    String higherEDU;
    String status;

    Payment payment;

    public Registration(String registrationId, String name, String address, String city, String postalCode, String mobile, String email, Date dob, String school) {
        this.registrationId = registrationId;
        this.name = name;
        this.address = address;
        this.city = city;
        this.postalCode = postalCode;
        this.mobile = mobile;
        this.email = email;
        this.dob = dob;
        this.school = school;
    }

    public Registration() {}
    public Payment getPayment() {
        return payment;
    }
    public void setPayment(Payment payment) {
        this.payment = payment;
    }
    public Registration(String registrationId, String nic, String batchId, String courseId, String gardianId, String name, String address, String city, String postalCode, String mobile, String email, Date dob, String gender, String school, String higherEDU, String status) {
        this.registrationId = registrationId;
        this.nic = nic;
        this.batchId = batchId;
        this.courseId = courseId;
        this.gardianId = gardianId;
        this.name = name;
        this.address = address;
        this.city = city;
        this.postalCode = postalCode;
        this.mobile = mobile;
        this.email = email;
        this.dob = dob;
        this.gender = gender;
        this.school = school;
        this.higherEDU = higherEDU;
        this.status = status;
    }

    public Registration(String registrationId, String name, String mobile, String email, String status) {
        this.registrationId = registrationId;
        this.name = name;
        this.mobile = mobile;
        this.email = email;
        this.status = status;
    }

    public String getRegistrationId() {
        return registrationId;
    }

    public String getNic() {
        return nic;
    }

    public String getBatchId() {
        return batchId;
    }

    public String getCourseId() {
        return courseId;
    }

    public String getGardianId() {
        return gardianId;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getMobile() {
        return mobile;
    }

    public String getEmail() {
        return email;
    }

    public Date getDob() {
        return dob;
    }

    public String getGender() {
        return gender;
    }

    public String getSchool() {
        return school;
    }

    public String getHigherEDU() {
        return higherEDU;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Registration{" +
                "registrationId='" + registrationId + '\'' +
                ", nic='" + nic + '\'' +
                ", batchId='" + batchId + '\'' +
                ", courseId='" + courseId + '\'' +
                ", gardianId='" + gardianId + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", dob=" + dob +
                ", gender='" + gender + '\'' +
                ", school='" + school + '\'' +
                ", higherEDU='" + higherEDU + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
