package id.my.aspian.l012;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DirectoryAdapter extends RecyclerView.Adapter<DirectoryAdapter.ViewHolder> {
    private final Context context;
    private final ArrayList<Directory> directories;

    public DirectoryAdapter(Context context, ArrayList<Directory> directories) {
        this.context = context;
        this.directories = directories;
    }

    @NonNull
    @Override
    public DirectoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.item_directory, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DirectoryAdapter.ViewHolder holder, int position) {
        Directory directory = this.directories.get(position);

        holder.directoryName.setText(directory.getName());
        holder.itemCount.setText(String.format("%s items", directory.getCount()));
        holder.directorySize.setText(directory.getSize());
        holder.itemView.setOnClickListener(view -> {
            if (this.context instanceof MainActivity) {
                ListVideoFragment fragment = ListVideoFragment.newInstanceByDirectory(directory.getPath());
                ((MainActivity) this.context).getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_frame, fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return directories.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView directoryName, itemCount, directorySize;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            directoryName = itemView.findViewById(R.id.directory_name);
            itemCount = itemView.findViewById(R.id.directory_count);
            directorySize = itemView.findViewById(R.id.directory_size);
        }
    }
}
