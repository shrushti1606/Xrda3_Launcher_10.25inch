package com.xrda3.xrda3_launcher.music;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xrda3.xrda3_launcher.R;
import com.xrda3.xrda3_launcher.music.adapter.CategoryAdapter;
import com.xrda3.xrda3_launcher.music.adapter.SongAdapter;
import com.xrda3.xrda3_launcher.music.model.CategoryModel;
import com.xrda3.xrda3_launcher.music.model.SongModel;

import java.util.ArrayList;

public class MusicFavActivity extends AppCompatActivity {

    private RecyclerView recyclerCategories, recyclerSongs;
    private CategoryAdapter categoryAdapter;
    private SongAdapter songAdapter;

    private ArrayList<CategoryModel> categoryList;
    private ArrayList<SongModel> songList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_fav);

        recyclerCategories = findViewById(R.id.recyclerCategories);
        recyclerSongs = findViewById(R.id.recyclerSongs);

        setupCategoryData();
        setupSongData();

        setupCategoryRecycler();
        setupSongRecycler();
    }

    private void setupCategoryRecycler() {
        recyclerCategories.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        );
        categoryAdapter = new CategoryAdapter(categoryList);
        recyclerCategories.setAdapter(categoryAdapter);
    }

    private void setupSongRecycler() {
        recyclerSongs.setLayoutManager(new LinearLayoutManager(this));
        songAdapter = new SongAdapter(this, songList);
        recyclerSongs.setAdapter(songAdapter);
    }

    private void setupCategoryData() {
        categoryList = new ArrayList<>();
        categoryList.add(new CategoryModel("On Repeat", "Songs you love"));
        categoryList.add(new CategoryModel("Weekly Top", "Most played"));
        categoryList.add(new CategoryModel("Energy Mix", "High tempo"));
        categoryList.add(new CategoryModel("Chill", "Relax vibes"));
    }

    private void setupSongData() {
        songList = new ArrayList<>();
        songList.add(new SongModel("Happy Music", "M83", "2:07", R.raw.happymusic1));
        songList.add(new SongModel("Blinding Lights", "The Weeknd", "2:19", R.raw.happymusic2));
        songList.add(new SongModel("Midnight City", "M83", "4:03", R.raw.midnight_city));
        songList.add(new SongModel("Morning City", "Daft Punk", "1:44", R.raw.morningcity1));
    }
}
