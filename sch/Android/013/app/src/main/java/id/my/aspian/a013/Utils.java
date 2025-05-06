package id.my.aspian.a013;

import android.annotation.SuppressLint;
import android.os.CountDownTimer;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class Utils {
    public static CountDownTimer makeTimer(TextView mTextField, long start, Callback callback) {
        // Start in second
        return new CountDownTimer(start * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long seconds = millisUntilFinished / 1000;
                long remainingSeconds = seconds % 60;
                @SuppressLint("DefaultLocale") String time = String.format("%02d:%02d", seconds / 60, remainingSeconds);
                mTextField.setText(time);
            }

            @Override
            public void onFinish() {
                mTextField.setText("00:00");
                callback.onDone();
            }
        };
    }

    public static void move(FragmentManager fm, Fragment instance) {
        fm.beginTransaction()
                .setCustomAnimations(
                        R.anim.slide_in_right, R.anim.slide_out_left,
                        R.anim.slide_in_left, R.anim.slide_out_right)
                .replace(R.id.main_frame, instance)
                .commit();
    }
}