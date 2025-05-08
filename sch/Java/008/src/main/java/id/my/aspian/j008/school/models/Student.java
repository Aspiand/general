/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.my.aspian.j008.school.models;

/**
 *
 * @author ao
 */
public class Student {

    private String sin; // Student Identification Number
    private String name;
    private String gender;
    private String grade;

    public void setSin(String sin) {
        this.sin = sin;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSin() {
        return sin;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getGrade() {
        return grade;
    }

    public String getMajor() {
        return major;
    }

    public String getAddress() {
        return address;
    }
    private String major;
    private String address;
}
