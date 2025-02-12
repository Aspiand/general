package id.my.aspian.l012;

import android.net.Uri;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.media3.common.MediaItem;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.ui.PlayerView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PlayerActivity extends AppCompatActivity {
    PlayerView playerView;
    ExoPlayer player;
    int position = -1;

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
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Toolbar
        ActionBar toolbar = getSupportActionBar();
        if (toolbar != null) {
            toolbar.setDisplayHomeAsUpEnabled(true);
        }

        // Player
        position = getIntent().getIntExtra("position", -1);
        String path = Utils.getAllVideo(this).get(position).getPath();
        MediaItem mediaItem = MediaItem.fromUri(Uri.parse(path));

        player = new ExoPlayer.Builder(this).build();
        playerView = findViewById(R.id.player);
        playerView.setPlayer(player);

        player.setMediaItem(mediaItem);
        player.prepare();

        // Recyclerview
        RecyclerView listVideo = findViewById(R.id.video_list);
        ArrayList<Video> videoFiles = Utils.getAllVideo(this);
        VideoAdapter adapter = new VideoAdapter(this, videoFiles);
        listVideo.setAdapter(adapter);
        listVideo.setLayoutManager(
                new LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        );
    }
}