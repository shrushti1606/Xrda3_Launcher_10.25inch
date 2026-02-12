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

public class ChillSongsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    SongAdapter adapter;
    ArrayList<SongModel> songList;
    ImageView click_home, click_down, click_set, click_heart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chill_songs);

        click_home = findViewById(R.id.click_home);
        click_down = findViewById(R.id.click_down);
        click_set = findViewById(R.id.click_set);
        click_heart = findViewById(R.id.click_heart);
        click_home.setOnClickListener(v ->
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


        songList.add(new SongModel("Namami Shamishay","jubin","4:06",R.raw.namami,R.drawable.shivay));
        songList.add(new SongModel("Happy Music", "M83", "2:07", R.raw.happymusic1,R.drawable.song1));
        songList.add(new SongModel("Blinding Lights", "The Weeknd", "2:19", R.raw.happymusic2, R.drawable.song2));
        songList.add(new SongModel("Midnight City", "M83", "4:03", R.raw.midnight_city,R.drawable.song3));
        songList.add(new SongModel("Dosti", "Punk", "1:44",R.raw.dosti, R.drawable.dosti));
        songList.add(new SongModel("Will Be", "Daft Punk", "1:44", R.raw.will_be,R.drawable.willbe));
        songList.add(new SongModel("Gulabo", "Daft Punk", "1:44", R.raw.gulabo,R.drawable.gulabo));
        songList.add(new SongModel("Morning City", "Daft Punk", "1:44", R.raw.sadmusic3,R.drawable.song6));
        songList.add(new SongModel("Achytam","keshavprakash","4:06",R.raw.achyutam,R.drawable.krishn_img));
        songList.add(new SongModel("Hey keshav","prakash","4:06",R.raw.hey_keshav,R.drawable.krishn_img));
        songList.add(new SongModel("Chupke Se","sonu","4:06",R.raw.chupkese,R.drawable.chupkese));
        songList.add(new SongModel("Basari","jubin","4:06",R.raw.basari,R.drawable.basari));




        adapter = new SongAdapter(this, songList);
        recyclerView.setAdapter(adapter);
    }
}
