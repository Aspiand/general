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
import java.util.List;

public class ListVideoFragment extends Fragment {
    ArrayList<Video> videos;
    VideoAdapter videoAdapter;

    public ListVideoFragment() {}



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        videos = Utils.getAllVideoByDirectory(requireContext(), "/storage/emulated/0/Share");
        videos = Utils.getAllVideoByDirectory(requireContext(), "/storage/emulated/0/Share/YouTube/Linux/20190216 IPv6 penggunaan ufw firewall.mkv");
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

    @SuppressLint("NotifyDataSetChanged")
    public void updateVideoFiles(List<Video> newVideos) {
        videos.clear();
        videos.addAll(newVideos);
        videoAdapter.notifyDataSetChanged();
    }
}