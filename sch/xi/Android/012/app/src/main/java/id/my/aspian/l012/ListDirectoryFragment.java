package id.my.aspian.l012;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListDirectoryFragment extends Fragment {
    ArrayList<Directory> videos;
    DirectoryAdapter directoryAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        videos = Utils.getAllVideoDirectory(
                Utils.getAllVideo(requireContext())
        );

        directoryAdapter = new DirectoryAdapter(requireContext(), videos);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_directory, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        RecyclerView listDirectory = view.findViewById(R.id.list_directory);

        if (videos != null && !videos.isEmpty()) {
            listDirectory.setAdapter(directoryAdapter);
            listDirectory.setLayoutManager(
                    new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false)
            );
        }
    }

//    private ArrayList<Directory> getAllVideoPath() {
//        Uri uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
//        String[] projection = {MediaStore.Video.VideoColumns.DATA};
//        String orderBy = MediaStore.Video.Media.DISPLAY_NAME + " ASC";
//        ArrayList<String> tmp = new ArrayList<>();
//
//        Cursor cursor = requireContext().getContentResolver().query(uri, projection, null, null, orderBy);
//        if (cursor != null && cursor.moveToFirst()) {
//            do {
//                tmp.add(cursor.getString(0));
//            } while (cursor.moveToNext());
//
//            cursor.close();
//        }
//
//        return tmp;
//    }
}