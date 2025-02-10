package id.my.aspian.j005;

import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DriverManager;

public class Koneksi {

    private static Connection conn;

    public static Connection koneksi() {
        if (conn != null) {
            return conn;
        }

        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/general",
                    "root", "root"
            );
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal terkoneksi");
        }

        return conn;
    }
}
