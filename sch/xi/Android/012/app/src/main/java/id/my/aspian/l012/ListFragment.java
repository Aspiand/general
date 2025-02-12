package id.my.aspian.l012;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class ListFragment extends Fragment {

    public ListFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        init(view);

        return view;
    }

    private void init(View view) {
        RecyclerView listVideo = view.findViewById(R.id.list_video);
        ArrayList<Video> videoFiles = Utils.getAllVideo(requireContext());

        if (!videoFiles.isEmpty()) {
            VideoAdapter adapter = new VideoAdapter(getContext(), videoFiles);
            listVideo.setAdapter(adapter);
            listVideo.setLayoutManager(
                    new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false)
            );
        }
    }
}