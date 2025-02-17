package id.my.aspian.l009;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Koneksi extends SQLiteOpenHelper {
    public static final String DB_NAME = "efisien_jar_ihsan";
    public static final String TABLE_NAME = "transaksi";

    public Koneksi(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "status TEXT," +
                        "jumlah LONG," +
                        "keterangan TEXT," +
                        "tanggal DATE DEFAULT CURRENT_DATE)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }
}