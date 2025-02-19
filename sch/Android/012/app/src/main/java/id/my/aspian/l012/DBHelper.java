package id.my.aspian.l012;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {


    private final String VIDEO_TABLE =
            "CREATE TABLE IF NOT EXISTS videos (" +
                    "path TEXT," +
                    "description TEXT," +
                    "is_starred BOOLEAN" +
            ")";
    private final String HISTORY_TABLE =
            "CREATE TABLE IF NOT EXISTS history (" +
                    "path TEXT," +
                    "timestamp INTEGER" +
            ")";

    public DBHelper(@Nullable Context context) {
        super(context, "apcb", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(this.VIDEO_TABLE);
        db.execSQL(this.HISTORY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        for (String tableName : new String[]{"videos", "history"}) {
            db.execSQL("DROP TABLE IF EXISTS " + tableName);
        }

        this.onCreate(db);
    }

    public void addToHistory(SQLiteDatabase db, String path, String time) {
        db.execSQL("INSERT INTO history VALUES (?, ?)", new String[]{path, time});
    }

    public void addToHistory(SQLiteDatabase db, String path, long time) {
        addToHistory(db, path, String.valueOf(time));
    }

    public void getHistory(SQLiteDatabase db) {
//        List<Video> videos = new ArrayList<>();
//        Cursor cursor = db.rawQuery("SELECT * FROM history DESC", null);
//        if (cursor.moveToFirst()) {
//            do {
//                videos.add(
//                        new Video()
//                );
//            } while (cursor.moveToNext());
//            cursor.close();
//        }
    }
}
