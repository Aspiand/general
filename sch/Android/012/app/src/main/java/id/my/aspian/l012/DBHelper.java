package id.my.aspian.l012;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DBHelper extends SQLiteOpenHelper {

    private final String VIDEO_TABLE =
            "CREATE TABLE IF NOT EXISTS videos (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "path TEXT," +
                    "is_starred BOOLEAN DEFAULT FALSE," +
                    "timestamp BIGINT DEFAULT NULL" +
                    ")";
    private final String COMMENT_TABLE =
            "CREATE TABLE IF NOT EXISTS comments (" +
                    "id INTEGER," +
                    "comment TEXT," +
                    "FOREIGN KEY (id) REFERENCES videos (id) ON UPDATE CASCADE ON DELETE CASCADE" +
                    ")";

    private static DBHelper instance;

    public static synchronized DBHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DBHelper(context.getApplicationContext());
        }

        return instance;
    }

    public DBHelper(@Nullable Context context) {
        super(context, "apcb", null, 4);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(this.VIDEO_TABLE);
        db.execSQL(this.COMMENT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS videos");
        db.execSQL("DROP TABLE IF EXISTS comments");
        this.onCreate(db);
    }

    public void deletes() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM videos");
        db.execSQL("DELETE FROM comments");
    }

    public void clearHistory() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE videos SET timestamp = NULL");
    }

    public void clearFavorite() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE videos SET is_starred = FALSE");
    }

    public void addAll(List<Video> videos) {
        SQLiteDatabase db = this.getWritableDatabase();
        videos.forEach(v -> {
            db.execSQL("INSERT INTO videos (path) VALUES (?)", new String[]{v.getPath()});
        });
    }

    public boolean isTableEmpty() {
        SQLiteDatabase db = this.getReadableDatabase();
        try (Cursor cursor = db.rawQuery("SELECT 1 FROM videos LIMIT 1", null)) {
            return !cursor.moveToFirst();
        }
    }

    public void addToHistory(String path, String time) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE videos SET timestamp = ? WHERE path = ?", new String[]{time, path});
    }

    public void addToHistory(String path, long time) {
        addToHistory(path, String.valueOf(time));
    }

    public void favorite(String path) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE videos SET is_starred = NOT is_starred WHERE path = ?", new String[]{path});
    }

    public ArrayList<Video> getAllFavorite(ArrayList<Video> videos) {
        Set<String> favorite = new HashSet<>();
        ArrayList<Video> videoList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT path FROM videos WHERE is_starred = TRUE";

        try (Cursor cursor = db.rawQuery(query, null)) {
            while (cursor.moveToNext()) {
                favorite.add(cursor.getString(0));
            }
        }

        videos.forEach(v -> {
            if (favorite.contains(v.getPath())) {
                videoList.add(v);
            }
        });

        return videoList;
    }

    public ArrayList<Video> getAllFavorite(Context context) {
        return getAllFavorite(Utils.getAllVideo(context));
    }

//    public ArrayList<Video> getAllHistory(Context context, SQLiteDatabase db, List<Video> videos) {
//        List<String> history = new ArrayList<>();
//        Cursor cursor = db.rawQuery("SELECT path, timestamp FROM videos WHERE timestamp != NULL", null);
//        if (cursor.moveToFirst()) {
//            do {
//                history.add(cursor.);
//            } while (cursor.moveToNext());
//
//            cursor.close();
//        }
//    }

//    public void getHistory(SQLiteDatabase db) {
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
//    }
}
