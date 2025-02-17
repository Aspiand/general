package id.my.aspian.l008;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private EditText Rnum1, Rnum2, Eoutput;
    double input1, input2, output = 0;

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

        Rnum1 = findViewById(R.id.input1);
        Rnum2 = findViewById(R.id.input2);
        Eoutput = findViewById(R.id.output);

        findViewById(R.id.tambah).setOnClickListener(v -> calculate("+"));
        findViewById(R.id.kurang).setOnClickListener(v -> calculate("-"));
        findViewById(R.id.kali).setOnClickListener(v -> calculate("*"));
        findViewById(R.id.bagi).setOnClickListener(v -> calculate("/"));
        findViewById(R.id.pangkat).setOnClickListener(v -> calculate("^"));
        findViewById(R.id.modulus).setOnClickListener(v -> calculate("mod"));

        findViewById(R.id.clear).setOnClickListener(v -> {
            Rnum1.setText("");
            Rnum2.setText("");
            Eoutput.setText("");

            Rnum1.requestFocus();
        });

        findViewById(R.id.close).setOnClickListener(v -> {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Exit?");
            alert.setMessage("Ingin menutup aplikasi ini?");
            alert.setPositiveButton("Inggih", (dialog, which) -> finish());
            alert.setNegativeButton("Kada", (dialog, which) -> {}).show();
        });
    }

    public void calculate(String operator) {
        if (Rnum1.getText().toString().isEmpty() || Rnum2.getText().toString().isEmpty()) {
            Toast.makeText(this, "Input Kosong!!", Toast.LENGTH_SHORT).show();

            Rnum1.requestFocus();
            Rnum2.requestFocus();

            return;
        }

        input1 = Double.parseDouble(Rnum1.getText().toString());
        input2 = Double.parseDouble(Rnum2.getText().toString());

        switch (operator) {
            case "+":
                output = input1 + input2;
                break;

            case "-":
                output = input1 - input2;
                break;

            case "*":
                output = input1 * input2;
                break;

            case "/":
                if (input2 == 0) {
                    Toast.makeText(this, "Tidak bisa dibagi nol", Toast.LENGTH_SHORT).show();
                    break;
                }

                output = input1 / input2;
                break;

            case "^":
                output = Math.pow(input1, input2);
                break;

            case "mod":
                output = input1 % input2;
                break;
        }

        Eoutput.setText(String.valueOf(output));
    }
}