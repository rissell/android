package com.tuesday.class5;

/**
 * Created by gdaalumno on 2/9/16.
 */
public class Student {

    private String name;
    private String major;
    private float grade;

    public Student( String name, String major, float grade){
        this.name = name;
        this.major = major;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }
}
