package id.my.aspian.latihan001;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

// Intent
import android.content.Intent;
import android.net.Uri;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        // Github Intent
        findViewById(R.id.btn_github).setOnClickListener(v -> {
            Intent Github = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/Aspiann17"));
            startActivity(Github);
        });

        // Youtube Intent
        findViewById(R.id.btn_youtube).setOnClickListener(v -> {
            // Intent YouTube = new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtube.com/"));
            Intent YouTube = new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtu.be/dQw4w9WgXcQ?si=LEHzRe_Dh6cavWQ6"));
            startActivity(YouTube);
        });

        // Shopee Intent
        findViewById(R.id.btn_shopee).setOnClickListener(v -> {
            Intent Shoppe = new Intent(Intent.ACTION_VIEW, Uri.parse("https://shopee.co.id/"));
            startActivity(Shoppe);
        });

        // Telepon Intent
        // Jangan lupa ganti nomor pada /app/res/values/strings.xml
        findViewById(R.id.btn_call).setOnClickListener(v -> {
            String phone_number = String.format("tel:%s", R.string.phone_number);
            Intent Call = new Intent(Intent.ACTION_CALL, Uri.parse(phone_number));
            startActivity(Call);
        });

        // Termux Intent
        findViewById(R.id.btn_termux).setOnClickListener(v -> {
            Intent Termux = getPackageManager().getLaunchIntentForPackage("com.termux");
            if (Termux != null) {
                startActivity(Termux);
            }
        });

        // Nix on Droid Intent
        findViewById(R.id.btn_nod).setOnClickListener(v -> {
            Intent Nod = getPackageManager().getLaunchIntentForPackage("com.termux.nix");
            if (Nod != null) {
                startActivity(Nod);
            }
        });

        // Facebook Intent
        findViewById(R.id.btn_facebook).setOnClickListener(v -> {
            Intent Facebook = new Intent(Intent.ACTION_VIEW, Uri.parse("https://facebook.com"));
            startActivity(Facebook);
        });

        // Design Intent
        findViewById(R.id.btn_design).setOnClickListener(v -> {
            Intent Design = getPackageManager().getLaunchIntentForPackage("id.my.aspian.latihan001d");
            if (Design != null) {
                startActivity(Design);
            }
        });

        // Browser Intent
        findViewById(R.id.btn_browser).setOnClickListener(v -> {
//            Intent Browser = new Intent(Intent.ACTION_VIEW, Uri.parse("https://kernel.org"));
//            startActivity(Browser);
            Intent intent = new Intent(getApplicationContext(), RelativeActivity.class);
            startActivity(intent);
        });
    }
}