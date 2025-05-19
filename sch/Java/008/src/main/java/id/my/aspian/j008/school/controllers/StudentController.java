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
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author ao
 */
public class StudentController {

    private StudentTableModel tableModel;
    private StudentView viewModel;
    public boolean saveButtonStatus = true; // if add else false

    public StudentController(StudentView v) {
        this.tableModel = new StudentTableModel();
        this.viewModel = v;

        // Center text in table
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
        this.viewModel.studentTable.setDefaultRenderer(String.class, renderer);
        this.viewModel.studentTable.setModel(tableModel);

    }

    public void setMaximumFrame() {
        try {
            viewModel.setMaximum(true);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
    }

    public void setUndecorated() {
        viewModel.putClientProperty("JInternalFrame.isPallete", Boolean.TRUE);
        ((BasicInternalFrameUI) viewModel.getUI()).setNorthPane(null);
    }

    public void refresh() {
        this.tableModel.setList(Student.getAll());

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

        String query = "SELECT * FROM " + Student.TABLE_NAME + " WHERE " + searchBy.toLowerCase() + " LIKE ?";

        List<Student> students = new ArrayList<>();
        try (PreparedStatement stmt = DBConnection.getDatabaseConnection().prepareStatement(query)) {
            stmt.setString(1, "%" + searchInput + "%");
            ResultSet results = stmt.executeQuery();

            while (results.next()) {
                students.add(Student.newInstance(results));
            }

            this.tableModel.setList(students);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void save() {
        Student student = new Student(
                StudentView.inputSin.getText().toString(),
                StudentView.inputName.getText().toString(),
                (StudentView.inputGenderMan.isSelected() ? "Man" : "Woman"),
                StudentView.inputClass.getSelectedItem().toString(),
                StudentView.inputMajor.getSelectedItem().toString(),
                StudentView.inputAddress.getText().toString()
        );

        if (this.saveButtonStatus) {
            student.insert();
        } else {
            student.update();
        }
    }
}
