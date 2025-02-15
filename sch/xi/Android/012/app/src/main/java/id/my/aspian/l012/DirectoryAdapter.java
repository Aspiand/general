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
    private final ArrayList<String> directoryList;

    public DirectoryAdapter(Context context, ArrayList<String> directoryList) {
        this.context = context;
        this.directoryList = directoryList;
    }

    @NonNull
    @Override
    public DirectoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.item_directory, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DirectoryAdapter.ViewHolder holder, int position) {
        String directory = this.directoryList.get(position);

        holder.directoryName.setText(directory);
    }

    @Override
    public int getItemCount() {
        return directoryList.size();
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
