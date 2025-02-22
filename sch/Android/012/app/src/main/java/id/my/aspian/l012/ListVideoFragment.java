package id.my.aspian.l012;

import android.database.sqlite.SQLiteDatabase;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListVideoFragment extends Fragment {
    ArrayList<Video> videos;
    public VideoAdapter videoAdapter;
    DBHelper conn;
    SQLiteDatabase db;

    public ListVideoFragment() {}

    public static ListVideoFragment newInstanceByFavorite() {
        ListVideoFragment fragment = new ListVideoFragment();
        Bundle args = new Bundle();
        args.putString("show", "favorite");
        fragment.setArguments(args);
        return fragment;
    }

    public static ListVideoFragment newInstanceByDirectory(String directory) {
        ListVideoFragment fragment = new ListVideoFragment();
        Bundle args = new Bundle();
        args.putString("show", "directory");
        args.putString("directory", directory);
        fragment.setArguments(args);
        return fragment;
    }

    public static ListVideoFragment newInstanceByDirectory() {
        return newInstanceByDirectory("/storage/emulated/0/Share");
    }

    @Override
    public void onDestroy() {
        db.close();
        conn.close();
        super.onDestroy();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        conn = new DBHelper(requireContext());
        db = conn.getWritableDatabase();
        if (conn.isTableEmpty(db)) {
            conn.addAll(db, videos);
        }

        if (getArguments() != null) {
            switch (getArguments().getString("show")) {
                case "favorite":
                    videos = conn.getAllFavorite(db, MainActivity.videos);
                    break;
                case "directory":
                    videos = Utils.getAllVideoByDirectory(
                            requireContext(),
                            getArguments().getString("directory")
                    );

                    break;
            }
        } else {
            videos = MainActivity.videos;
        }

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

        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView,
                                  @NonNull RecyclerView.ViewHolder viewHolder,
                                  @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                Video video = videos.get(position);

                conn.favorite(db, video.getPath());
                videoAdapter.notifyItemChanged(position);
            }

            @Override
            public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView,
                                    @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY,
                                    int actionState, boolean isCurrentlyActive) {
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(listVideo);

        listVideo.setAdapter(videoAdapter);
        listVideo.setLayoutManager(
                new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false)
        );
    }
}