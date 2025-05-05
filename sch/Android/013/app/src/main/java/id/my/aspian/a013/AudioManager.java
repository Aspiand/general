package id.my.aspian.a013;

import android.content.Context;
import android.media.MediaPlayer;

import java.util.HashMap;
import java.util.Map;

public class AudioManager {
    private static AudioManager instance;
    private final Context context;
    private MediaPlayer backgroundPlayer;
    private final Map<Integer, MediaPlayer> effects = new HashMap<>();


    private AudioManager(Context ctx) {
        this.context = ctx.getApplicationContext();
    }

    public static synchronized AudioManager getInstance(Context ctx) {
        if (instance == null) {
            instance = new AudioManager(ctx);
        }

        return instance;
    }

    public void playBackground(int resId) {
        backgroundPlayer = MediaPlayer.create(context, resId);
        backgroundPlayer.setLooping(true);
        backgroundPlayer.start();
    }

    public void stopBackground() {
        if (backgroundPlayer == null) return;
        if (backgroundPlayer.isPlaying()) backgroundPlayer.stop();

        backgroundPlayer.release();
        backgroundPlayer = null;
    }

    public void play(int resId) {
        MediaPlayer mp = effects.get(resId);

        if (mp == null) {
            mp = MediaPlayer.create(context, resId);
            effects.put(resId, mp);
        }

        mp.start();
    }

    public void relase() {
        stopBackground();
        for (MediaPlayer mp : effects.values()) mp.release();
        effects.clear();
    }
}
