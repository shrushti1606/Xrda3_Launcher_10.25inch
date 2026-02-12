package com.xrda3.xrda3_launcher.video;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xrda3.xrda3_launcher.R;

import java.util.ArrayList;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {

    private final Context context;
    private final ArrayList<Integer> videoResources;

    public VideoAdapter(Context context, ArrayList<Integer> videoResources) {
        this.context = context;
        this.videoResources = videoResources;
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the item layout (video_item.xml)
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.video_item, parent, false);
        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {

        int videoResId = videoResources.get(position);

        // Get video title from resource name
        String title = context.getResources().getResourceEntryName(videoResId);
        holder.videoName.setText(title);

        // Open VideoPlayerActivity on click
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, VideoPlayerActivity.class);
            intent.putExtra("videoResId", videoResId);
            intent.putExtra("videoTitle", title);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return videoResources.size();
    }

    static class VideoViewHolder extends RecyclerView.ViewHolder {

        TextView videoName;

        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            // ✅ Correct ID from video_item.xml
            videoName = itemView.findViewById(R.id.videoName);
        }
    }
}
