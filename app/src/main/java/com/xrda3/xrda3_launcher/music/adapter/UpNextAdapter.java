package com.xrda3.xrda3_launcher.music.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xrda3.xrda3_launcher.R;
import com.xrda3.xrda3_launcher.music.model.SongModel;

import java.util.ArrayList;

public class UpNextAdapter extends RecyclerView.Adapter<UpNextAdapter.ViewHolder> {

    ArrayList<SongModel> list;

    public UpNextAdapter(ArrayList<SongModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_song, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SongModel song = list.get(position);
        holder.title.setText(song.getTitle());
        holder.artist.setText(song.getArtist());
        holder.imgSong.setImageResource(song.getImageResId());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, artist;
        ImageView  imgSong;
        ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.songSong);
            artist = itemView.findViewById(R.id.songArtist);
            imgSong = itemView.findViewById(R.id.imgsong);
        }
    }
}

