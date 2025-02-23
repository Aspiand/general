package id.my.aspian.l012;

import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.media3.common.MediaItem;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.ui.PlayerView;

import java.util.Objects;

public class PlayerActivity extends AppCompatActivity {
    DBHelper conn;
    SQLiteDatabase db;
    PlayerView playerView;
    ExoPlayer player;
    String path, title, size;

    @Override
    protected void onDestroy() {
        conn.addToHistory(db, path, player.getCurrentPosition());
        db.close();
        conn.close();
        player.release();
        super.onDestroy();
    }

    @Override
    protected void onStart() {
        player.setPlayWhenReady(true);
        super.onStart();
    }

    @Override
    protected void onStop() {
        player.setPlayWhenReady(false);
        super.onStop();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_player);
        Objects.requireNonNull(getSupportActionBar()).hide();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Get intent data
        path = getIntent().getStringExtra("path");
        title = getIntent().getStringExtra("title");
        size = getIntent().getStringExtra("size");

        // Database
        conn = new DBHelper(this);
        db = conn.getWritableDatabase();

        // Player
        player = new ExoPlayer.Builder(this).build();
        playerView = findViewById(R.id.player);
        playerView.setKeepScreenOn(true);
        playerView.setPlayer(player);

        MediaItem mediaItem = MediaItem.fromUri(Uri.parse(path));

        player.setMediaItem(mediaItem);
        player.prepare();
        player.setPlayWhenReady(true);

        ((TextView) findViewById(R.id.title)).setText(title);
        ((TextView) findViewById(R.id.size)).setText(size);

//        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, new ListVideoFragment()).commit();
    }
}