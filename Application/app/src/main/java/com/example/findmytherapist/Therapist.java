package com.example.findmytherapist;


// This is the class for the "search therapist" recycler view

public class Therapist {
    private String Fname, Lname, gender, availabilities, platforms, specialities;

    public Therapist() {
    }

    public Therapist(String Fname, String Lname, String gender, String availabilities, String platforms, String specialities) {
        this.Fname = Fname;
        this.Lname = Lname;
        this.gender = gender;
        this.availabilities = availabilities;
        this.platforms = platforms;
        this.specialities = specialities;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAvailabilities() {
        return availabilities;
    }

    public void setAvailabilities(String availabilities) {
        this.availabilities = availabilities;
    }

    public String getPlatforms() {
        return platforms;
    }

    public void setPlatforms(String platforms) {
        this.platforms = platforms;
    }

    public String getSpecialities() {
        return specialities;
    }

    public void setSpecialities(String specialities) {
        this.specialities = specialities;
    }
}
