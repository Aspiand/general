package id.my.aspian.j007;

import java.sql.*;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Utils {
    private static Connection connection = null;

    public static synchronized Connection getDatabaseConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/general",
                        "root", "root");
            } catch (SQLException e) {
                showErrorDialog("Can't connect to database");
                e.printStackTrace();
                System.exit(1);
            }
        }

        return connection;
    }

    public static void closeDatabaseConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void clearInputs(JTextField... inputs) {
        for (JTextField input : inputs) {
            input.setText("");
        }
    }

    public static void clearTable(javax.swing.table.DefaultTableModel model) {
        for (int i = model.getRowCount() - 1; i >= 0; i--) {
            model.removeRow(i);
        }
    }

    public static void showErrorDialog(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    // Validation
    public static int integerField(JTextField... fields) {
        for (JTextField f : fields) {
            f.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    char c = e.getKeyChar();
                    if (!Character.isDigit(c)) {
                        e.consume();
                    }
                }
            });
        }
        return -1;
    }

    public static int stringField(JTextField... fields) {
        for (JTextField f : fields) {
            f.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    char c = e.getKeyChar();
                    if (Character.isWhitespace(c) && f.getText().isEmpty()) {
                        e.consume();
                    }
                }
            });
        }
        return -1;
    }
}