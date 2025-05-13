/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.my.aspian.j008.school.controllers;

import id.my.aspian.j008.school.view.HomeView;
import id.my.aspian.j008.school.view.StudentView;
import static java.awt.Frame.MAXIMIZED_BOTH;
import javax.swing.JOptionPane;

/**
 *
 * @author ao
 */
public class HomeController {

    HomeView view;

    public HomeController(HomeView hv) {
        this.view = hv;
    }

    public void setFullScreen() {
        this.view.setExtendedState(MAXIMIZED_BOTH);
    }

    public void quitAction() {
        if (JOptionPane.showConfirmDialog(this.view, "Quit?", "Warning!", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    public void showStudentViewAction() {
        StudentView sv = new StudentView();
        this.view.homePanel.add(sv);
        sv.setVisible(true);
    }
}
