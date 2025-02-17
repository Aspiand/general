package id.my.aspian.l008;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class KubusActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_kubus);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText Rsisi = findViewById(R.id.sisi_kubus);
        EditText Ovolume = findViewById(R.id.volume_kubus);
        EditText Oluas = findViewById(R.id.luas_kubus);

        findViewById(R.id.hitung).setOnClickListener(v -> {
            if (Rsisi.getText().toString().isEmpty()) {
                Toast.makeText(this, "Input Tidak Boleh Kosong!!", Toast.LENGTH_SHORT).show();
                Rsisi.requestFocus();
                return;
            }

            double sisi = Double.parseDouble(Rsisi.getText().toString());
            Ovolume.setText(String.format("%.02f cm3", sisi * sisi * sisi));
            Oluas.setText(String.format("%.02f cm2", 4 * (sisi * sisi)));
        });

        findViewById(R.id.reset).setOnClickListener(v -> {
            Rsisi.setText("");
            Ovolume.setText("");
            Oluas.setText("");

            Rsisi.requestFocus();
        });
    }
}