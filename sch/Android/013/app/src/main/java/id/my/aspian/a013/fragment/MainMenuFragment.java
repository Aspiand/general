package id.my.aspian.a013.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import id.my.aspian.a013.GamePreferences;
import id.my.aspian.a013.R;

public class MainMenuFragment extends Fragment {

    public MainMenuFragment() {
    }

    public static MainMenuFragment newInstance() {
        return new MainMenuFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main_menu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.math).setOnClickListener(v -> {
            Fragment stage = StageFragment.newMathInstance();
//            Utils.move(requireActivity().getSupportFragmentManager(), R.id.main_frame, stage);
            requireActivity().getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(
                            R.anim.slide_in_right, R.anim.slide_out_left,
                            R.anim.slide_in_left, R.anim.slide_out_right)
                    .replace(R.id.main_frame, stage)
                    .addToBackStack(null)
                    .commit();
        });

        view.findViewById(R.id.comming_soon).setOnClickListener(v -> {
            GamePreferences.getInstance(requireContext()).clearAll();
            Toast.makeText(requireContext(), "Reset...", Toast.LENGTH_SHORT).show();
        });
    }
}