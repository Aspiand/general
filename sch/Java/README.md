# Example
## Membuat koneksi dengan mysql
```java
import java.sql.*;
```
```java
try {
    DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
    conn = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/general",
        "root", "root"
    );
} catch (SQLException e) {
    e.printStackTrace();
}
```

## Menghapus semua data yang ditampilkan pada JTable

```java
public void deleteAllDataFromTable() {
    int rc = tabelModel.getRowCount();
    for (int i = rc - 1; i >= 0; i--) {
        tabelModel.removeRow(i);
    }
}
```
