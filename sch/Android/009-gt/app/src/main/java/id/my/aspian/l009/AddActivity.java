package id.my.aspian.l009;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddActivity extends AppCompatActivity {
    Koneksi koneksi;
    String status = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText Rvalue = findViewById(R.id.jumlah);
        EditText Rketerangan = findViewById(R.id.keterangan);
        koneksi = new Koneksi(this);

        ((RadioGroup) findViewById(R.id.status)).setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.masuk) status = "Masuk";
            else if (checkedId == R.id.keluar) status = "Keluar";
        });

        findViewById(R.id.simpan).setOnClickListener(v -> {
            String jumlah = Rvalue.getText().toString();
            String keterangan = Rketerangan.getText().toString();

            if (status.isEmpty()) toast("Pilih Status!!");
            else if (jumlah.isEmpty()) toast("Isi Nilai");
            else if (keterangan.isEmpty()) toast("Isi Keterangan");
            else {
                SQLiteDatabase db = koneksi.getWritableDatabase();
                db.execSQL(
                        "INSERT INTO " + Koneksi.TABLE_NAME + " (status, jumlah, keterangan) VALUES (?, ?, ?)",
                        new String[] {status, jumlah, keterangan}
                );

                toast("Data berhasil ditambahkan coyy!!!!");
                finish();
            }
        });
    }

    public void toast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}