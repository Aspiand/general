package id.my.aspian.l008;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class BangunRuangActivity extends AppCompatActivity {
    String[] menu_bangun_ruang = {"Kubus", "Balok", "Tabung", "Bola"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bangun_ruang);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ListView menu_ruang = findViewById(R.id.menu_ruang);
        ArrayAdapter<String> data = new ArrayAdapter<>(this, R.layout.list_menu, menu_bangun_ruang);
        menu_ruang.setAdapter(data);

        menu_ruang.setOnItemClickListener((parent, view, position, id) -> {
            switch (position) {
                case 0: startActivity(new Intent(this, KubusActivity.class));
                case 1: startActivity(new Intent(this, KubusActivity.class));
            }
        });
    }
}