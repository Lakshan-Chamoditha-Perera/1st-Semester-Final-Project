package lk.ijse.studentsmanagement.to;

public class Subject {
    String id;
    String name;
    String hours;

    public Subject(String id, String name, String hours) {
        this.id = id;
        this.name = name;
        this.hours = hours;
    }

    public Subject(String id) {
        this.id = id;
    }

    public Subject() {
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", hours='" + hours + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getHours() {
        return hours;
    }
}
