package com.xrda3.xrda3_launcher.music;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xrda3.xrda3_launcher.R;
import com.xrda3.xrda3_launcher.music.adapter.SongAdapter;
import com.xrda3.xrda3_launcher.music.model.SongModel;

import java.util.ArrayList;

public class VibeSongsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    SongAdapter adapter;
    ArrayList<SongModel> songList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vibe_songs);



        recyclerView = findViewById(R.id.recyclerSongs);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        songList = new ArrayList<>();

        songList.add(new SongModel("Travelling", "M83", "2:07", R.raw.travelling));
        songList.add(new SongModel("Blinding Lights", "The Weeknd", "2:19", R.raw.happymusic2));
        songList.add(new SongModel("Midnight City", "M83", "4:03", R.raw.midnight_city));
        songList.add(new SongModel("Morning City", "Daft Punk", "1:44", R.raw.morningcity1));
        songList.add(new SongModel("Sad", "Daft Punk", "1:44", R.raw.sadmusic1));
        songList.add(new SongModel("Morning City", "Daft Punk", "1:44", R.raw.sadmusic3));



        adapter = new SongAdapter(this, songList);
        recyclerView.setAdapter(adapter);
    }
}
