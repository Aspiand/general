package id.my.aspian.j006;

import javax.swing.JTextField;

public class Bola {

    double volume, alas;

    Bola(JTextField field) {
        double jari = Double.parseDouble(field.getText());

        this.volume = Math.pow(jari, 3) * Math.PI * 4/3;
        this.alas = 4 * Math.PI * Math.pow(jari, 2);
    }
    
    public String getVolume() {
        return String.format("%.2f", this.volume);
    }
    
    public String getAlas() {
        return String.format("%.2f", this.alas);
    }
}
