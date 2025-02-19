package id.my.aspian.j006;

import javax.swing.JTextField;

public class Persegi {

    public double luasPersegi, kelilingPersegi;

    Persegi(JTextField field) {
        double sisi = Double.valueOf(field.getText());
        this.luasPersegi = sisi * sisi;
        this.kelilingPersegi = 4 * sisi;
    }

    public String getLuasPersegi() {
        return String.format("%.2f cm", this.luasPersegi);
    }

    public String getKelilingPersegi() {
        return String.format("%.2f cm2", this.kelilingPersegi);
    }
}
