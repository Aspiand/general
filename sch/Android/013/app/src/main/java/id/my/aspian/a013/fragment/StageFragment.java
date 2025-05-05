package id.my.aspian.a013.fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.appbar.MaterialToolbar;

import id.my.aspian.a013.GamePreferences;
import id.my.aspian.a013.R;
import id.my.aspian.a013.Utils;


public class StageFragment extends Fragment {
    GamePreferences preferences;


    public StageFragment() {}

    public static StageFragment newMathInstance() {
        return new StageFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferences = GamePreferences.getInstance(requireContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_stage, container, false);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MaterialToolbar toolbar = view.findViewById(R.id.bar);
        toolbar.setNavigationOnClickListener(v -> {
            requireActivity().getSupportFragmentManager().popBackStack();
        });

        Dialog dialog = new Dialog(requireContext());
        dialog.setContentView(R.layout.message_dialog);
        dialog.findViewById(R.id.back_button).setOnClickListener(v -> {
            dialog.dismiss();
            requireActivity().getSupportFragmentManager().popBackStack();
        });

        Utils.makeTimer(view.findViewById(R.id.timer), 5, () -> {
            preferences.setScore(200);

            ((TextView) dialog.findViewById(R.id.current_score)).setText(String.format(
                    "Current Score: %d", preferences.getScore()
            ));

            ((TextView) dialog.findViewById(R.id.best_score)).setText(String.format(
                    "Best Score: %d", preferences.getBestScore()
            ));

            dialog.show();
        }).start();
    }
}