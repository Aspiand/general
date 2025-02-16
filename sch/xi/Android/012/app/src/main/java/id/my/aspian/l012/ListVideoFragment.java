package id.my.aspian.l012;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListVideoFragment extends Fragment {
    ArrayList<Video> videos;
    VideoAdapter videoAdapter;
    ArrayList<Directory> directories;

    public ListVideoFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        videos = Utils.getAllVideo(requireContext());
        videoAdapter = new VideoAdapter(getContext(), videos);
        directories = Utils.getAllVideoDirectory(videos);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_video, container, false);
        init(view);

        return view;
    }

    private void init(View view) {
        RecyclerView listVideo = view.findViewById(R.id.list_video);

        if (!videos.isEmpty()) {
            listVideo.setAdapter(videoAdapter);
            listVideo.setLayoutManager(
                    new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false)
            );
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    public void updateVideoFiles(int position) {
        if (videos != null) {
            videos.clear();
            videos.addAll(directories.get(position).getVideoOnDirectory());
            videoAdapter.notifyDataSetChanged();
        }
    }
}