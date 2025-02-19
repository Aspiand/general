package id.my.aspian.j006;

import javax.swing.JTextField;

public class PersegiPanjang {
    double luas, keliling;

    public PersegiPanjang(JTextField Rpanjang, JTextField Rlebar) {
        double panjang = Double.parseDouble(Rpanjang.getText());
        double lebar = Double.parseDouble(Rlebar.getText());
        
        this.luas = panjang * lebar;
        this.keliling = 2 * (panjang + lebar);
    }
    
    public String getLuas() {
        return String.format("%.2f", this.luas);
    }
    
    public String getKeliling() {
        return String.format("%.2f", this.keliling);
    }
}
