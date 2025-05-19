/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.my.aspian.j008.school.utils;

import java.sql.*;

/**
 *
 * @author ao
 */
public class DBConnection {

    private static Connection connection = null;

    public static synchronized Connection getDatabaseConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/j008",
                        "root", "root");
            } catch (SQLException e) {
                System.out.println("Can't connect to database");
                // e.printStackTrace();
                System.exit(1);
            }
        }

        return connection;
    }
}
