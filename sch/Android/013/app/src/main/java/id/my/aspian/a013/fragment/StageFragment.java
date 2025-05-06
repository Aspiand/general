package id.my.aspian.a013.fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.List;

import id.my.aspian.a013.AudioManager;
import id.my.aspian.a013.GamePreferences;
import id.my.aspian.a013.Question;
import id.my.aspian.a013.QuestionGenerator;
import id.my.aspian.a013.R;
import id.my.aspian.a013.Utils;

public class StageFragment extends Fragment {
    QuestionGenerator questionGenerator;
    Question question;
    GamePreferences preferences;
    Dialog dialog;
    CountDownTimer timer;

    public StageFragment() {}

    public static StageFragment newMathInstance() {
        return new StageFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferences = GamePreferences.getInstance(requireContext());
        questionGenerator = new QuestionGenerator();
        question = questionGenerator.generate(100);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_stage, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MaterialToolbar toolbar = view.findViewById(R.id.bar);
        toolbar.setNavigationOnClickListener(v -> {
            timer.cancel();
            requireActivity().getSupportFragmentManager().popBackStack();
        });

        int timeout = preferences.getTimeout() == 0 ? 15 : preferences.getTimeout();
        timer = Utils.makeTimer(view.findViewById(R.id.timer), timeout, this::gameover);

        dialog = new Dialog(requireContext());
        dialog.setContentView(R.layout.message_dialog);
        dialog.findViewById(R.id.back_button).setOnClickListener(v -> {
            dialog.dismiss();
            requireActivity().getSupportFragmentManager().popBackStack();
        });
        dialog.findViewById(R.id.restart_button).setOnClickListener(v -> {
            dialog.dismiss();
            Fragment stage = StageFragment.newMathInstance();
            Utils.move(requireActivity().getSupportFragmentManager(), stage);
        });

        ((TextView) view.findViewById(R.id.score)).setText(String.valueOf(preferences.getScore()));
        timer.start();

        // Questions
        Button[] buttons = new Button[]{
                view.findViewById(R.id.button_a),
                view.findViewById(R.id.button_b),
                view.findViewById(R.id.button_c),
                view.findViewById(R.id.button_d),
        };

        List<String> options = question.getOptions();
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setText(options.get(i));
            buttons[i].setOnClickListener(v -> {
                if (question.isCorrect(((Button) v).getText().toString())) {
                    preferences.setScore(preferences.getScore() + 10);
                    preferences.addTimeout(5);
                    timer.cancel();
                    timer = null;

                    AudioManager.getInstance(requireContext()).play(R.raw.success);
                    Fragment stage = StageFragment.newMathInstance();
                    Utils.move(requireActivity().getSupportFragmentManager(), stage);
                } else {
                    preferences.setScore(0);
                    preferences.setTimeout(15);
                    AudioManager.getInstance(requireContext()).play(R.raw.failed);
                    gameover();
                }
            });
        }

        ((TextView) view.findViewById(R.id.question)).setText(question.getText());
    }

    @SuppressLint("DefaultLocale")
    void gameover() {
        ((TextView) dialog.findViewById(R.id.current_score)).setText(String.format(
                "Current Score: %d", preferences.getScore()
        ));

        ((TextView) dialog.findViewById(R.id.best_score)).setText(String.format(
                "Best Score: %d", preferences.getBestScore()
        ));

        timer.cancel();
        timer = null;
        dialog.show();
    }
}