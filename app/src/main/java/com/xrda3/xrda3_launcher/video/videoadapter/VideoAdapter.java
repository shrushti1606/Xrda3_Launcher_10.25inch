package com.xrda3.xrda3_launcher.video.videoadapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;   // recyclerView cannot work without an adapter file

import com.xrda3.xrda3_launcher.R;
import com.xrda3.xrda3_launcher.video.model.VideoModel;
import com.xrda3.xrda3_launcher.video.ui.VideoPlayerActivity;

import java.util.ArrayList;   // stores multiple videos

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {    // this class will provide views (cards) for recycler view
                                // how many items ? , how to craete an view ? , how to bind an data ?
    Context context;   // needed to inflate layout and open activity
    ArrayList<VideoModel> list;   // contains all videos to show

    public VideoAdapter(Context context, ArrayList<VideoModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType) {

        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_video, parent, false);

        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull VideoViewHolder holder,
            int position) {

        VideoModel video = list.get(position);

        holder.title.setText(video.title);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, VideoPlayerActivity.class);
            intent.putExtra("videoUri", video.videoUri.toString());
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class VideoViewHolder extends RecyclerView.ViewHolder {

        TextView title;

        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.videoTitle);
        }
    }
}
