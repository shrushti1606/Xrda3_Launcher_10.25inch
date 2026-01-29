package com.xrda3.xrda3_launcher.music.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xrda3.xrda3_launcher.R;
import com.xrda3.xrda3_launcher.music.MusicPlayerActivity;
import com.xrda3.xrda3_launcher.music.model.SongModel;

import java.util.ArrayList;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongViewHolder> {

    private final Context context;
    private final ArrayList<SongModel> songList;

    public SongAdapter(Context context, ArrayList<SongModel> songList) {
        this.context = context;
        this.songList = songList;
    }

    @NonNull
    @Override
    public SongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_song, parent, false);
        return new SongViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SongViewHolder holder, int position) {
        SongModel song = songList.get(position);

        holder.songSong.setText(song.getTitle());
        holder.songArtist.setText(song.getArtist());
        holder. songTime.setText(song.getDuration());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, MusicPlayerActivity.class);
            intent.putExtra("title", song.getTitle());
            intent.putExtra("artist", song.getArtist());
            intent.putExtra("songResId", song.getSongResId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return songList.size();
    }

    static class SongViewHolder extends RecyclerView.ViewHolder {

        TextView songSong, songArtist, songTime;

        public SongViewHolder(@NonNull View itemView) {
            super(itemView);
            songSong = itemView.findViewById(R.id.songSong);
            songArtist = itemView.findViewById(R.id.songArtist);
            songTime = itemView.findViewById(R.id.songTime);
        }
    }
}
