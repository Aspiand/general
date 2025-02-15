package id.my.aspian.l012;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class ListFragment extends Fragment {
    ArrayList<Video> videoFiles;
    VideoAdapter videoAdapter;

    public ListFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        videoFiles = Utils.getAllVideo(requireContext());
        videoAdapter = new VideoAdapter(getContext(), videoFiles);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        init(view);

        return view;
    }

    private void init(View view) {
        Toast.makeText(requireContext(), "ehe", Toast.LENGTH_SHORT).show();
        RecyclerView listVideo = view.findViewById(R.id.list_video);

        if (!videoFiles.isEmpty()) {
            listVideo.setAdapter(videoAdapter);
            listVideo.setLayoutManager(
                    new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false)
            );
        }
    }

    private void updateVideoFiles() {
        videoFiles.clear();
        videoFiles.addAll(Utils.getAllVideo(requireContext()));
//        videoAdapter.notifyDataSetChanged();
    }
}