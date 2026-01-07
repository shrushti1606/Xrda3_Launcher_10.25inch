package com.xrda3.xrda3_launcher.video.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xrda3.xrda3_launcher.R;
import com.xrda3.xrda3_launcher.video.model.VideoModel;

import java.util.ArrayList;

public class FolderAdapter extends RecyclerView.Adapter<FolderAdapter.Holder> {

    private ArrayList<String> folderList;
    private ArrayList<VideoModel> videoList;
    private Context context;

    public FolderAdapter(ArrayList<String> folderList,
                         ArrayList<VideoModel> videoList,
                         Context context) {
        this.folderList = folderList;
        this.videoList = videoList;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.folder_view, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        String folder = folderList.get(position);
        holder.name.setText(folder);

        int count = 0;
        for (VideoModel model : videoList) {
            if (model.getFolder().equals(folder)) count++;
        }
        holder.count.setText(count + " videos");
    }

    @Override
    public int getItemCount() {
        return folderList.size();
    }

    static class Holder extends RecyclerView.ViewHolder {
        TextView name, count;

        Holder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.folderName);
            count = itemView.findViewById(R.id.videosCount);
        }
    }
}
