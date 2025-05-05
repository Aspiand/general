package id.my.aspian.a013.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.appbar.MaterialToolbar;

import id.my.aspian.a013.GamePreferences;
import id.my.aspian.a013.R;
import id.my.aspian.a013.Utils;


public class StageFragment extends Fragment {

    public StageFragment() {
    }

    public static StageFragment newmathinstance() {
        return new StageFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
            requireActivity().getSupportFragmentManager().popBackStack();
        });

        Utils.makeTimer(view.findViewById(R.id.timer), 5, () -> {
            Toast.makeText(requireContext(), "ehe", Toast.LENGTH_SHORT).show();
            GamePreferences.getInstance(requireContext()).setScore(0);
        }).start();
    }
}