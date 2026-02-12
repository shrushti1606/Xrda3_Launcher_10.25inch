package com.xrda3.xrda3_launcher.music;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xrda3.xrda3_launcher.R;
import com.xrda3.xrda3_launcher.music.adapter.SongAdapter;
import com.xrda3.xrda3_launcher.music.model.SongModel;

import java.util.ArrayList;

public class WorkoutSongActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    SongAdapter adapter;
    ArrayList<SongModel> songList;
    ImageView click_home, click_menu, click_down, click_set, click_heart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_song);

        click_home = findViewById(R.id.click_home);
        click_down = findViewById(R.id.click_down);
        click_set = findViewById(R.id.click_set);
        click_heart = findViewById(R.id.click_heart);
        click_home.setOnClickListener(v ->
                startActivity(new Intent(this, MusicHomeActivity.class)));

        click_menu.setOnClickListener(v ->
                startActivity(new Intent(this, MusicHomeActivity.class)));

        click_down.setOnClickListener(v ->
                startActivity(new Intent(this, MusicDownloadActivity.class)));

        click_set.setOnClickListener(v ->
                startActivity(new Intent(this, MusicSettingsActivity.class)));

        click_heart.setOnClickListener(v ->
                startActivity(new Intent(this, MusicFavActivity.class)));


        recyclerView = findViewById(R.id.recyclerSongs);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        songList = new ArrayList<>();

        songList.add(new SongModel("Happy Music", "M83", "2:07", R.raw.happymusic1,R.drawable.song1));
        songList.add(new SongModel("Blinding Lights", "The Weeknd", "2:19", R.raw.happymusic2, R.drawable.song2));
        songList.add(new SongModel("Midnight City", "M83", "4:03", R.raw.midnight_city,R.drawable.song3));
        songList.add(new SongModel("Morning City", "Daft Punk", "1:44",R.raw.morningcity1, R.drawable.song4));
        songList.add(new SongModel("Morning City", "Daft Punk", "1:44", R.raw.morningcity2,R.drawable.song5));
        songList.add(new SongModel("Sad", "Daft Punk", "1:44", R.raw.sadmusic1,R.drawable.artist));
        songList.add(new SongModel("Morning City", "Daft Punk", "1:44", R.raw.sadmusic3,R.drawable.song6));



        adapter = new SongAdapter(this, songList);
        recyclerView.setAdapter(adapter);
    }
}
