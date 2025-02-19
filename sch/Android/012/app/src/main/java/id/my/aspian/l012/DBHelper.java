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
                    "is_starred BOOLEAN DEFAULT NULL," +
                    "timestamp INTEGER DEFAULT NULL" +
            ")";

    public DBHelper(@Nullable Context context) {
        super(context, "apcb", null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(this.VIDEO_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS videos");
        this.onCreate(db);
    }

    public void delete(SQLiteDatabase db) {
        db.execSQL("DELETE FROM videos");
    }

    public void addAll(SQLiteDatabase db, List<Video> videos) {
        for (Video v : videos) {
            db.execSQL("INSERT INTO videos (path) VALUES (?)", new String[]{v.getPath()});
        }
    }

    public boolean isTableEmpty(SQLiteDatabase db) {
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM videos", null);
        if (cursor.moveToFirst()) {
            boolean v = cursor.getInt(0) == 0;;
            cursor.close();
            return v;
        }

        return false;
    }

    public void addToHistory(SQLiteDatabase db, String path, String time) {
        db.execSQL("UPDATE videos SET timestamp = ? WHERE path = ?", new String[]{time, path});
    }

    public void addToHistory(SQLiteDatabase db, String path, long time) {
        addToHistory(db, path, String.valueOf(time));
    }

    public void addToFavorite(SQLiteDatabase db, String path) {
        db.execSQL("UPDATE videos SET is_starred = TRUE WHERE path = ?", new String[]{path});
    }

    public void deleteFavorite(SQLiteDatabase db, String path) {
        db.execSQL("UPDATE videos SET is_starred = FALSE WHERE path = ?", new String[]{path});
    }

    public ArrayList<Video> getAllFavorite(SQLiteDatabase db, List<Video> videos) {
        List<String> favorite = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT path FROM videos WHERE is_starred = 1", null);
        if (cursor.moveToFirst()) {
            do {
                favorite.add(cursor.getString(0));
            } while (cursor.moveToNext());

            cursor.close();
        }

        ArrayList<Video> videoList = new ArrayList<>();
        for (Video v : videos) {
            if (favorite.contains(v.getPath())) {
                videoList.add(v);
            }
        }

        return videoList;
    }

    public ArrayList<Video> getAllFavorite(Context context, SQLiteDatabase db) {
        return getAllFavorite(db, Utils.getAllVideo(context));
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
