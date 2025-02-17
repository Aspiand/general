package id.my.aspian.l009;

import android.app.DatePickerDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Calendar;

public class UpdateActivity extends AppCompatActivity {
    Koneksi koneksi;
    String status = "";

    EditText Rvalue, Rketerangan, Rdate;
    RadioGroup Rstatus;
    RadioButton Rmasuk, Rkeluar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_update);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        koneksi = new Koneksi(this);

        Rmasuk = findViewById(R.id.masuk);
        Rkeluar = findViewById(R.id.keluar);
        Rvalue = findViewById(R.id.jumlah);
        Rketerangan = findViewById(R.id.keterangan);
        Rdate = findViewById(R.id.date);
        Rstatus = findViewById(R.id.status);

        date_picker(this, Rdate);
        findViewById(R.id.update).setOnClickListener(v -> update());
        Rstatus.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.masuk) status = "Masuk";
            else if (checkedId == R.id.keluar) status = "Keluar";
        });

        init();
    }

    public void update() {
        String value = Rvalue.getText().toString();
        String keterangan = Rketerangan.getText().toString();
        String date = Rdate.getText().toString();

        if (status.isEmpty()) MainActivity.toast(this, "Pilih Status!!");
        else if (value.isEmpty()) MainActivity.toast(this, "Isi Nilai");
        else if (keterangan.isEmpty()) MainActivity.toast(this, "Isi Keterangan");
        else if (date.isEmpty()) MainActivity.toast(this, "Isi tanggal");
        else {
            SQLiteDatabase db = koneksi.getWritableDatabase();
            db.execSQL(
                    "UPDATE " + Koneksi.TABLE_NAME + " SET status = ?, jumlah = ?, keterangan = ?, tanggal = ? WHERE id = ?",
                    new String[] {status, value, keterangan, date}
            );

            MainActivity.toast(this, "Data berhasil diubah");
            finish();
        }
    }

    public void init() {
        SQLiteDatabase db = koneksi.getReadableDatabase();
        Cursor cursor = db.rawQuery(String.format(
                "SELECT * FROM %s WHERE id = '%s'",
                Koneksi.TABLE_NAME, MainActivity.item_id
        ), null);

        if (cursor != null && cursor.moveToFirst()) {

            if (cursor.getString(1).equals("Masuk")) {
                Rmasuk.setChecked(true);
            } else {
                Rkeluar.setChecked(true);
            }

            Rvalue.setText(cursor.getString(2));
            Rketerangan.setText(cursor.getString(3));
            Rdate.setText(cursor.getString(4));

            cursor.close();
        }
    }

    public static void date_picker(Context context, EditText object) {
        Calendar calendar = Calendar.getInstance();
        NumberFormat decimal_format = new DecimalFormat("00");

        object.setOnClickListener(v -> {
            DatePickerDialog date_dialog = new DatePickerDialog(context, (view, year, month, dayOfMonth) -> {
                object.setText(year + "-" + decimal_format.format(month + 1) + "-" + decimal_format.format(dayOfMonth));
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

            date_dialog.show();
        });
    }
}