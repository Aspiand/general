package id.my.aspian.j006;

import javax.swing.JTextField;

public class Balok {
    double volume, alas;
    
    Balok(JTextField Rpanjang, JTextField Rlebar, JTextField Rtinggi) {
        double panjang = Double.parseDouble(Rpanjang.getText());
        double lebar = Double.parseDouble(Rlebar.getText());
        double tinggi = Double.parseDouble(Rtinggi.getText());
        
        this.volume = panjang * lebar * tinggi;
        this.alas = 2 * ((panjang * lebar) + (panjang * tinggi) + (lebar * tinggi));
    }
    
    public String getVolume() {
        return String.format("%.2f", this.volume);
    }
    
    public String getAlas() {
        return String.format("%.2f", this.alas);
    }
}
