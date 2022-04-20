package com.example.findmytherapist;

// this is the class for the "my clients" recycler view

public class Client {

    private String Fname, Lname, age, gender;

    public Client() {
    }

    public Client(String Fname, String Lname, String age, String gender) {
        this.Fname = Fname;
        this.Lname = Lname;
        this.age = age;
        this.gender = gender;
    }

    public String getFname() {
        return Fname;
    }

    public void setFname(String fname) {
        Fname = fname;
    }

    public String getLname() {
        return Lname;
    }

    public void setLname(String lname) {
        Lname = lname;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
