package id.my.aspian.l012;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListDirectoryFragment extends Fragment {
    ArrayList<Directory> directories;
    DirectoryAdapter directoryAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayList<Video> videos = Utils.getAllVideo(requireContext());
        directories = Utils.getAllVideoDirectory(videos);
        directoryAdapter = new DirectoryAdapter(requireContext(), directories);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_directory, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        RecyclerView listDirectory = view.findViewById(R.id.list_directory);

        if (directories != null && !directories.isEmpty()) {
            listDirectory.setAdapter(directoryAdapter);
            listDirectory.setLayoutManager(
                    new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false)
            );
        }
    }
}