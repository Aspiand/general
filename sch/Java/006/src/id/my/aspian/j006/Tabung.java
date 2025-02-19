package id.my.aspian.j006;

import javax.swing.JTextField;

public class Tabung {
    double volume, alas;

    Tabung(JTextField Rjari, JTextField Rtinggi) {
        double jari = Double.parseDouble(Rjari.getText());
        double tinggi = Double.parseDouble(Rtinggi.getText());

        this.volume = Math.PI * Math.pow(jari, 2) * tinggi;
        this.alas = 2 * Math.PI * jari * (jari + tinggi);
    }
    
    public String getVolume() {
        return String.format("%.2f", this.volume);
    }
    
    public String getAlas() {
        return String.format("%.2f", this.alas);
    }
}
