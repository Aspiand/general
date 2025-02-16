package id.my.aspian.l012;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DirectoryAdapter extends RecyclerView.Adapter<DirectoryAdapter.ViewHolder> {
    private final Context context;
    private final ArrayList<Directory> directories; // semua directory
    private final ArrayList<Directory> listDirectory; // directory yang ditampilkan

    public DirectoryAdapter(Context context, ArrayList<Directory> directories) {
        this.context = context;
        this.directories = directories;
        this.listDirectory = directories;
    }

    @NonNull
    @Override
    public DirectoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.item_directory, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DirectoryAdapter.ViewHolder holder, int position) {
        Directory directory = this.listDirectory.get(position);

        holder.directoryName.setText(directory.getName());
        holder.itemCount.setText(String.format("%s items", directory.getCount()));
    }

    @Override
    public int getItemCount() {
        return listDirectory.size();
    }

    public static class ViewHolder extends  RecyclerView.ViewHolder {
        TextView directoryName, itemCount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            directoryName = itemView.findViewById(R.id.directory_name);
            itemCount = itemView.findViewById(R.id.directory_count);
        }
    }
}
