package id.my.aspian.l012;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListFavoriteFragment extends Fragment {
    ArrayList<Video> videos;
    public VideoAdapter videoAdapter;
    DBHelper conn;

    public ListFavoriteFragment() {
    }

    @Override
    public void onDestroyView() {
        conn.close();
        super.onDestroyView();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        conn = DBHelper.getInstance(requireContext());

        videos = conn.getAllFavorite(requireContext());
        videoAdapter = new VideoAdapter(getContext(), videos);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_favorite, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        RecyclerView listVideo = view.findViewById(R.id.list_video);

        listVideo.setAdapter(videoAdapter);
        listVideo.setLayoutManager(
                new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false)
        );
    }
}