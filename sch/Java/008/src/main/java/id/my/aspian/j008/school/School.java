/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package id.my.aspian.j008.school;

import id.my.aspian.j008.school.view.HomeView;
import id.my.aspian.j008.school.utils.DBConnection;

/**
 *
 * @author ao
 */
public class School {
    static {
        DBConnection.getDatabaseConnection();
    }

    public static void main(String[] args) {
        HomeView hv = new HomeView();
        hv.setVisible(true);
    }
}
