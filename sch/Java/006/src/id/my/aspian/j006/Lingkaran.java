package id.my.aspian.j006;

import javax.swing.JTextField;

public class Lingkaran {
    public double luas, keliling;

    public Lingkaran(JTextField field) {
        double jari = Double.parseDouble(field.getText());
        this.luas = Math.PI * Math.pow(jari, 2);
        this.keliling = 2 * Math.PI * jari;
    }

    public String getLuas() {
        return String.format("%.2f", this.luas);
    }

    public String getKeliling() {
        return String.format("%.2f", this.keliling);
    }
}
