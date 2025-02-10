package id.my.aspian.l012;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {
    private Context context;
    private ArrayList<VideoFiles> videoFiles;

    private View view;

    public VideoAdapter(Context context, ArrayList<VideoFiles> videoFiles) {
        this.context = context;
        this.videoFiles = videoFiles;
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(context).inflate(R.layout.item_video, parent, false);
        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        VideoFiles video = videoFiles.get(position);

        holder.filename.setText(video.getTitle());
        holder.duration.setText(video.getDuration());
    }

    @Override
    public int getItemCount() {
        return videoFiles.size();
    }

    public static class VideoViewHolder extends RecyclerView.ViewHolder {
        ImageView thumbnail;
        TextView filename, duration;

        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);

            thumbnail = itemView.findViewById(R.id.thumbnail);
            filename = itemView.findViewById(R.id.filename);
            duration = itemView.findViewById(R.id.duration);
        }
    }
}
