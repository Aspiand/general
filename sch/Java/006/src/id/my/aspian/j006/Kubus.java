package id.my.aspian.j006;

import javax.swing.JTextField;

public class Kubus extends Persegi {

    private double luasKubus, volumeKubus;

    Kubus(JTextField field) {
        super(field);
        double sisi = Double.parseDouble(field.getText());
        this.luasKubus = 6 * luasPersegi;
        this.volumeKubus = sisi * luasPersegi;
    }

    public String getLuasKubus() {
        return String.format("%.2f cm", this.luasKubus);
    }
    
    public String getVolumeKubus() {
        return String.format("%.2f cm", this.volumeKubus);
    }
}
