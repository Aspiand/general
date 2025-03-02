package id.my.aspian.l012;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.MediaStore;

import androidx.annotation.Nullable;
import androidx.media3.common.util.Log;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DBHelper extends SQLiteOpenHelper {

    private final String VIDEO_TABLE =
            "CREATE TABLE IF NOT EXISTS videos (" +
                    "path TEXT," +
                    "is_starred BOOLEAN DEFAULT FALSE," +
                    "timestamp BIGINT DEFAULT NULL," +
                    "watched_at BIGINT DEFAULT NULL" +
                    ")";

    private static DBHelper instance;

    public static synchronized DBHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DBHelper(context.getApplicationContext());
        }

        return instance;
    }

    public DBHelper(@Nullable Context context) {
        super(context, "apcb", null, 12);
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

    public void deletes() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM videos");
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
        String query = "UPDATE videos SET timestamp = ?, watched_at = ? WHERE path = ?";
        String[] args = new String[]{time, String.valueOf(System.currentTimeMillis() / 1000L), path};
        db.execSQL(query, args);
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

    public ArrayList<Video> getAllHistory(ArrayList<Video> videos) {
        ArrayList<String> path = new ArrayList<>();
        ArrayList<String> timestamp = new ArrayList<>();
        ArrayList<Video> videoList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT path, timestamp FROM videos " +
                "WHERE timestamp IS NOT NULL ORDER BY path ASC";

        try (Cursor cursor = db.rawQuery(query, null)) {
            while (cursor.moveToNext()) {
                path.add(cursor.getString(0));
                timestamp.add(cursor.getString(1));
            }
        }

        for (int i = 0; i < videos.size() && i < timestamp.size(); i++) {
            Video video = videos.get(i);
            if (path.contains(video.getPath())) {
                videoList.add(video);
            }
        }

        return videoList;
    }

    public ArrayList<Video> getAllHistory(Context context) {
        HashMap<String, String> paths = new HashMap<>();
        ArrayList<Video> videoList;
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT path, timestamp FROM videos " +
                "WHERE timestamp IS NOT NULL ORDER BY watched_at DESC";

        try (Cursor cursor = db.rawQuery(query, null)) {
            while (cursor.moveToNext()) {
                paths.put(cursor.getString(0), cursor.getString(1));
            }
        }

        if (paths.isEmpty()) {
            return new ArrayList<>();
        }

        String selection = MediaStore.Video.Media.DATA + " IN (" + Utils.generatePlaceholders(paths.size()) + ")";
        String[] args = paths.keySet().toArray(new String[0]);
        videoList = Utils.getVideos(context, selection, args);
        return videoList;
    }

    public long getLastPlayed(String path) {
        SQLiteDatabase db = this.getReadableDatabase();
        try (Cursor cursor = db.rawQuery("SELECT timestamp FROM videos WHERE path = ?", new String[]{path})) {
            cursor.moveToNext();
            return cursor.getLong(0);
        }
    }
}
