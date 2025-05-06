package id.my.aspian.a013;

import android.content.Context;
import android.content.SharedPreferences;

public class GamePreferences {
    private static final String PREF_NAME = "halah";
    private static final String KEY_BEST_SCORE = "best_score";
    private static final String KEY_LAST_SCORE = "last_score";
    private static final String KEY_TIMEOUT = "timeout";

    private static GamePreferences instance;
    private final SharedPreferences prefs;

    private GamePreferences(Context context) {
        prefs = context.getApplicationContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public static synchronized GamePreferences getInstance(Context context) {
        if (instance == null) {
            instance = new GamePreferences(context);
        }

        return instance;
    }

    public void setScore(int score) {
        int currentBest = getBestScore();
        if (score > currentBest) {
            prefs.edit().putInt(KEY_BEST_SCORE, score).apply();
        }

        prefs.edit().putInt(KEY_LAST_SCORE, score).apply();
    }

    public int getScore() {
        return prefs.getInt(KEY_LAST_SCORE, 0);
    }

    public int getBestScore() {
        return prefs.getInt(KEY_BEST_SCORE, 0);
    }

    public int getTimeout() {
        return prefs.getInt(KEY_TIMEOUT, 15);
    }

    public void setTimeout(int time) {
        prefs.edit().putInt(KEY_TIMEOUT, time).apply();
    }

    public void addTimeout(int time) {
        setTimeout(getTimeout() + time);
    }

    public void clearAll() {
        prefs.edit().clear().apply();
    }
}
