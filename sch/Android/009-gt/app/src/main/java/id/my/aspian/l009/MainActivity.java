package id.my.aspian.l009;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    SwipeRefreshLayout swipeRefresh;
    TextView filter_indicator, saldo, masuk, keluar;
    ListView list_transaksi;

    ArrayList<HashMap<String, String>> arus_kas = new ArrayList<>();
    Koneksi koneksi;
    String data_query, total_query;

    public static String tanggal_dari, tanggal_sampai = "";
    public static String item_id = "";
    public static boolean filter = false;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_filter) {
            startActivity(new Intent(this, FilterActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        koneksi = new Koneksi(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        findViewById(R.id.add).setOnClickListener(v -> {
            startActivity(new Intent(this, AddActivity.class));
        });

        masuk = findViewById(R.id.pemasukan);
        keluar = findViewById(R.id.pengeluaran);
        saldo = findViewById(R.id.saldo);

        filter_indicator = findViewById(R.id.filter_indicator);
        filter_indicator.setVisibility(ListView.GONE);

        swipeRefresh = findViewById(R.id.refresh);
        swipeRefresh.setOnRefreshListener(() -> {
            filter = false;
            reload();
        });

        list_transaksi = findViewById(R.id.list_transaksi);
        list_transaksi.setOnItemClickListener((parent, view, position, id) -> {
            item_id = ((TextView) view.findViewById(R.id.id)).getText().toString();

            Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.list_menu);
            Objects.requireNonNull(dialog.getWindow()).setLayout(
                    WindowManager.LayoutParams.MATCH_PARENT,
                    WindowManager.LayoutParams.WRAP_CONTENT
            );

            dialog.show();

            dialog.findViewById(R.id.delete).setOnClickListener(v -> {
                dialog.dismiss();

                AlertDialog.Builder alert = new AlertDialog.Builder(this);
                alert.setTitle("Hapus Data? " + item_id);
                alert.setMessage("Yakin ingin menghapus data ini?");
                alert.setNegativeButton("False", (dialog1, which) -> toast(this, "Ehe"));
                alert.setPositiveButton("True", (dialog1, which) -> {
                    SQLiteDatabase db = koneksi.getWritableDatabase();
                    db.execSQL(String.format(
                            "DELETE FROM %s WHERE id = '%s'",
                            Koneksi.TABLE_NAME, item_id
                    ));

                    toast(this, "Data " + item_id + " berhasil dihapus!");
                    reload();
                });

                alert.show();
            });

            dialog.findViewById(R.id.update).setOnClickListener(v -> {
                dialog.dismiss();
                startActivity(new Intent(this, UpdateActivity.class));
            });
        });

        reload();
    }

    @Override
    protected void onResume() {
        super.onResume();
        reload();
    }

    private void reload() {
        arus_kas.clear();

        if (filter) {
            filter_indicator.setVisibility(ListView.VISIBLE);
            filter_indicator.setText(String.format("%s -> %s", tanggal_dari, tanggal_sampai));

            data_query = "SELECT *, STRFTIME('%d-%m-%Y', tanggal) AS tanggall FROM " + Koneksi.TABLE_NAME +
                            " WHERE (tanggal >= '" + tanggal_dari + "' ) AND (tanggal <= '" + tanggal_sampai + "')";

            total_query = "SELECT SUM(jumlah)," +
                    "(SELECT SUM(jumlah) FROM " + Koneksi.TABLE_NAME + " WHERE status = 'Masuk' AND (tanggal >= '" + tanggal_dari + "' ) AND (tanggal <= '" + tanggal_sampai + "')) as jumlah_masuk," +
                    "(SELECT SUM(jumlah) FROM " + Koneksi.TABLE_NAME + " WHERE status = 'Keluar' AND (tanggal >= '" + tanggal_dari + "' ) AND (tanggal <= '" + tanggal_sampai + "')) as jumlah_keluar" +
                    " FROM " + Koneksi.TABLE_NAME;
        } else {
            filter_indicator.setVisibility(ListView.GONE);

            data_query = "SELECT *, STRFTIME('%d-%m-%Y', tanggal) AS tanggall FROM " + Koneksi.TABLE_NAME;
            total_query = "SELECT SUM(jumlah)," +
                    "(SELECT SUM(jumlah) FROM " + Koneksi.TABLE_NAME + " WHERE status = 'Masuk') as jumlah_masuk," +
                    "(SELECT SUM(jumlah) FROM " + Koneksi.TABLE_NAME + " WHERE status = 'Keluar') as jumlah_keluar" +
                    " FROM " + Koneksi.TABLE_NAME;
        }

        show_data(data_query);
        show_total(total_query);

        swipeRefresh.setRefreshing(false);
    }

    private void show_data(String query) {
        SQLiteDatabase db = koneksi.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<>();
                map.put("id", cursor.getString(0));
                map.put("status", cursor.getString(1));
                map.put("jumlah", cursor.getString(2));
                map.put("keterangan", cursor.getString(3));
                map.put("tanggal", cursor.getString(5));

                arus_kas.add(map);
            } while (cursor.moveToNext());

            cursor.close();
        }

        SimpleAdapter simple_katanya = new SimpleAdapter(
                this, arus_kas, R.layout.list_items,
                new String[] {"id", "status", "jumlah", "keterangan", "tanggal"},
                new int[] {R.id.id, R.id.status, R.id.jumlah, R.id.keterangan, R.id.tanggal}
        );

        list_transaksi.setAdapter(simple_katanya);
    }

    private void show_total(String query) {
        SQLiteDatabase db = koneksi.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor != null && cursor.moveToFirst()) {
            double pemasukan = cursor.getDouble(1);
            double pengeluaran = cursor.getDouble(2);

            masuk.setText(String.valueOf(pemasukan));
            keluar.setText(String.valueOf(pengeluaran));
            saldo.setText(String.valueOf(pemasukan - pengeluaran));

            cursor.close();
        }
    }

    public static void toast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}