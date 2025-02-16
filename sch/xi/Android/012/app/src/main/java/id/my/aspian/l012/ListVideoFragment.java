package id.my.aspian.l012;

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

    public ListVideoFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainActivity activity = (MainActivity) getActivity();
        videos = activity != null ? activity.videos : Utils.getAllVideo(requireContext());
        videoAdapter = new VideoAdapter(getContext(), videos);
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

    private void updateVideoFiles() {
        videos.clear();
        videos.addAll(Utils.getAllVideo(requireContext()));
//        videoAdapter.notifyDataSetChanged();
    }
}