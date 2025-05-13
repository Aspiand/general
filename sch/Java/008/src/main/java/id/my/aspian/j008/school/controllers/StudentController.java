/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.my.aspian.j008.school.controllers;

import id.my.aspian.j008.school.models.Student;
import id.my.aspian.j008.school.models.StudentTableModel;
import id.my.aspian.j008.school.utils.DBConnection;
import id.my.aspian.j008.school.view.StudentView;
import java.beans.PropertyVetoException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicInternalFrameUI;

/**
 *
 * @author ao
 */
public class StudentController {

    private final StudentTableModel tableModel = new StudentTableModel();
    public boolean saveButtonStatus; // true if add else false

    public void setMaximumFrame(StudentView sv) {
        try {
            sv.setMaximum(true);
        } catch (PropertyVetoException ex) {
            ex.printStackTrace();
        }
    }

    public void setUndecorated(StudentView sv) {
        sv.putClientProperty("JInternalFrame.isPallete", Boolean.TRUE);
        ((BasicInternalFrameUI) sv.getUI()).setNorthPane(null);
    }

    public List<Student> getAllStudent() {
        List<Student> students = new ArrayList<>();
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

    public void refresh() {
        this.tableModel.setList(getAllStudent());
        for (JTextField field : new JTextField[]{
            StudentView.inputSin,
            StudentView.inputName,
            StudentView.inputSearch
        }) {
            field.setText("");
        }

        StudentView.inputAddress.setText("");
        StudentView.inputGenderMan.setSelected(true);
        StudentView.inputMajor.setSelectedIndex(0);
        StudentView.inputClass.setSelectedIndex(0);
        StudentView.inputSearchBy.setSelectedIndex(0);
    }

    public void search() {
        String searchBy = StudentView.inputSearchBy.getSelectedItem().toString();
        String searchInput = StudentView.inputSearch.getText().toString();

        String query = "SELECT * FROM ? WHERE ? LIKE %?%";
        String[] params = new String[]{Student.TABLE_NAME, searchBy.toLowerCase(), searchInput};

        List<Student> students = new ArrayList<>();
        try (PreparedStatement stmt = DBConnection.getDatabaseConnection().prepareStatement(query, params)) {
            ResultSet results = stmt.executeQuery();
            while (results.next()) {
                students.add(Student.newInstance(results));
            }

            this.tableModel.setList(students);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
