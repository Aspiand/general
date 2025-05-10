/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.my.aspian.j008.school.models;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author ao
 */
public class StudentTableModel extends AbstractTableModel {

    private List<Student> students = new ArrayList<>();

    public void setList(List<Student> ls) {
        this.students = ls;
        fireTableDataChanged();
    }

    public Student get(int row) {
        return this.students.get(row);
    }

    public void insert(Student s) {
        this.students.add(s);
        fireTableDataChanged();
    }

    public void update(int row, Student s) {
        this.students.set(row, s);
        fireTableDataChanged();
    }

    public void delete(int row) {
        this.students.remove(row);
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return this.students.size();
    }

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return rowIndex++;
            case 1:
                return students.get(rowIndex).getSin();
            case 2:
                return students.get(rowIndex).getName();
            case 3:
                return students.get(rowIndex).getGender();
            case 4:
                return students.get(rowIndex).getClass();
            case 5:
                return students.get(rowIndex).getMajor();
            case 6:
                return students.get(rowIndex).getAddress();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return new HashMap<String, String>() {
            {
                put("0", "No");
                put("1", "SIN");
                put("2", "Name");
                put("3", "Gender");
                put("4", "Class");
                put("5", "Major");
                put("6", "Address");
            }
        }.get(column);
    }
}
