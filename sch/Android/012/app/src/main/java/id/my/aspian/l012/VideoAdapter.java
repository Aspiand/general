package id.my.aspian.l012;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.ArrayList;
import java.util.Locale;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {
    private final Context context;
    private final ArrayList<Video> videos;

    public VideoAdapter(Context context, ArrayList<Video> videos) {
        this.context = context;
        this.videos = videos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.item_video, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Video video = this.videos.get(position);
        int duration = Integer.parseInt(video.getDuration()) / 1000;

        holder.filename.setText(video.getFileName());
        holder.size.setText(video.getReadableSize());
        holder.duration.setText(String.format(
                Locale.getDefault(), "%02d:%02d", duration / 60, duration % 60
        ));

        // Thumbnail
        Glide.with(holder.itemView.getContext())
                .asBitmap()
                .load(video.getPath())
                .override(112, 62)
                .into(holder.thumbnail);

        // Event
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(this.context, PlayerActivity.class);
            intent.putExtra("path", video.getPath());
            intent.putExtra("title", video.getTitle());
            intent.putExtra("directory", new File(video.getPath()).getParentFile().getName());
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            this.context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return this.videos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView thumbnail;
        TextView filename, duration, size;
        View status;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            thumbnail = itemView.findViewById(R.id.thumbnail);
            filename = itemView.findViewById(R.id.filename);
            duration = itemView.findViewById(R.id.duration);
            status = itemView.findViewById(R.id.status);
            size = itemView.findViewById(R.id.size);
        }
    }
}
