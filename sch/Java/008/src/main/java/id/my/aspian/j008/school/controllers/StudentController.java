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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.plaf.basic.BasicInternalFrameUI;

/**
 *
 * @author ao
 */
public class StudentController {

    private final StudentTableModel tableModel = new StudentTableModel();

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
    
    public void loadToTable() {
        this.tableModel.setList(getAllStudent());
    }
    
    public void reset() {
        loadToTable();
    }
}
