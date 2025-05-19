/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.my.aspian.j008.school.models;

import id.my.aspian.j008.school.utils.DBConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author ao
 */
public class Student {

    public static final String TABLE_NAME = "students";
    private String sin; // Student Identification Number
    private String name;
    private String gender;
    private String grade;
    private String major;
    private String address;

    public Student(String sin, String name, String gender, String grade, String major, String address) {
        this.sin = sin;
        this.name = name;
        this.gender = gender;
        this.grade = grade;
        this.major = major;
        this.address = address;
    }

    public static Student newInstance(ResultSet rs) throws SQLException {
        return new Student(
                rs.getString("sin"),
                rs.getString("name"),
                rs.getString("gender"),
                rs.getString("grade"),
                rs.getString("major"),
                rs.getString("address")
        );
    }

    public static ArrayList<Student> getAll() {
        ArrayList<Student> students = new ArrayList<>();
        String query = "SELECT * FROM " + Student.TABLE_NAME + " ORDER BY sin ASC";
        try (Statement stmt = DBConnection.getDatabaseConnection().createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                students.add(Student.newInstance(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return students;
    }

    public void insert() {
        String query = "INSERT INTO " + this.TABLE_NAME + " VALUES (?, ?, ?, ?, ?, ?)";
        String[] params = getArray();
        try (PreparedStatement stmt = DBConnection.getDatabaseConnection().prepareStatement(query)) {
            for (int i = 0; i < params.length; i++) {
                stmt.setString(i + 1, params[i]);
            }

            System.out.println(stmt.executeUpdate());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        String query = "UPDATE " + this.TABLE_NAME + " SET name = ?, gender = ?, grade = ?, major = ?, address = ?";
        String[] params = getArray();
        try (PreparedStatement stmt = DBConnection.getDatabaseConnection().prepareCall(query)) {
            for (int i = 0; i < params.length; i++) {
                stmt.setString(i + 1, params[i]);
            }

            System.out.println(stmt.executeUpdate());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String[] getArray() {
        return new String[]{this.sin, this.name, this.gender, this.grade, this.major, this.address};
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
}
