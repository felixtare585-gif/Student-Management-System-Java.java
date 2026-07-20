package model;

public class Student {

    private int id;
    private String name;
    private String course;
    private String phone;
    private String email;
    private String gender;


    public Student(int id, String name, String course,
                   String phone, String email, String gender) {

        this.id = id;
        this.name = name;
        this.course = course;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
    }


    public int getId() {
        return id;
    }


    public String getName() {
        return name;
    }


    public String getCourse() {
        return course;
    }


    public String getPhone() {
        return phone;
    }


    public String getEmail() {
        return email;
    }


    public String getGender() {
        return gender;
    }
}