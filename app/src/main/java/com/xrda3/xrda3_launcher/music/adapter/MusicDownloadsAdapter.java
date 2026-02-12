package com.xrda3.xrda3_launcher.music.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xrda3.xrda3_launcher.R;
import com.xrda3.xrda3_launcher.music.MusicPlayerActivity;
import com.xrda3.xrda3_launcher.music.model.MusicDownloadModel;

import java.util.ArrayList;

public class MusicDownloadsAdapter extends RecyclerView.Adapter<MusicDownloadsAdapter.ViewHolder> {

    private final ArrayList<MusicDownloadModel> list;
    private final Context context;

    public MusicDownloadsAdapter(Context context, ArrayList<MusicDownloadModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_download, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        MusicDownloadModel model = list.get(position);

        holder.txtName.setText(model.getName());
        holder.txtArtist.setText(model.getArtist());
        holder.txtSize.setText(model.getDuration());
        holder.imgAlbum.setImageResource(model.getAlbumImage());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, MusicPlayerActivity.class);
            intent.putExtra("title", model.getName());
            intent.putExtra("artist", model.getArtist());
            intent.putExtra("songResId", model.getSongResId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtName, txtArtist, txtSize;
        ImageView imgAlbum;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.txtName);
            txtArtist = itemView.findViewById(R.id.txtArtist);
            txtSize = itemView.findViewById(R.id.txtSize);
            imgAlbum = itemView.findViewById(R.id.imgmusic);
        }
    }
}
