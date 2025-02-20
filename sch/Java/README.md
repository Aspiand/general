# Guide
## Delete All Show Data
```java
public void deleteAllDataFromTable() {
    int rc = tabelModel.getRowCount();
    for (int i = rc - 1; i >= 0; i--) {
        tabelModel.removeRow(i);
    }
}
```
