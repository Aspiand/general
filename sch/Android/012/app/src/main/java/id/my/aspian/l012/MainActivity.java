package id.my.aspian.l012;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static ArrayList<Video> videos;
    private FragmentManager fragmentManager;
    public Fragment listVideoFragment, listDirectoryFragment, listFavoriteFragment;
    DBHelper conn;

    BottomNavigationView bottom_nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0);
            return insets;
        });

        init();

        // dev
        bottom_nav.setSelectedItemId(R.id.nav_home);
    }

    private void init() {
        // Database
        conn = DBHelper.getInstance(this);
        if (conn.isTableEmpty()) {
            conn.addAll(videos);
        }

        videos = Utils.getAllVideo(this);

        // Navigation
        bottom_nav = findViewById(R.id.bottom_navigation);
        bottom_nav.setOnItemSelectedListener(this::navHandler);

        // Fragment
        fragmentManager = getSupportFragmentManager();
        listVideoFragment = new ListVideoFragment();
        listDirectoryFragment = new ListDirectoryFragment();
        listFavoriteFragment = ListVideoFragment.newInstanceByFavorite();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.delete_favorite) {
            conn.clearFavorite();
            toast("Semua favorite dihapus");
        } else if (itemId == R.id.delete_history) {
            conn.clearHistory();
            toast("Semua history dihapus");
        } else {
            throw new IllegalStateException("Unexpected value: " + item.getItemId());
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private boolean navHandler(MenuItem item) {
        int item_id = item.getItemId();

        if (item.getItemId() == R.id.nav_home) {
            loadFragment(listDirectoryFragment);
            return true;
        } else if (item_id == R.id.nav_favorite) {
            loadFragment(listFavoriteFragment);
            return true;
        } else if (item_id == R.id.nav_video) {
            loadFragment(listVideoFragment);
            return true;
        } else if (item_id == R.id.nav_history) {
//            loadFragment(listVideoFragment);
            return true;
        }

        return false;
    }

    public void toast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void loadFragment(Fragment fragment) {
        fragmentManager.beginTransaction()
                .replace(R.id.main_frame, fragment)
                .setReorderingAllowed(true)
                .commit();
    }
}