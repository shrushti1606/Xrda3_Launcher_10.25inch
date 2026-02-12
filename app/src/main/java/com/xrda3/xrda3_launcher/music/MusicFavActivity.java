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
import com.xrda3.xrda3_launcher.music.FavouriteManager;

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

    private void setupCategoryData() {
        categoryList = new ArrayList<>();
        categoryList.add(new CategoryModel("On Repeat", "Songs you love"));
        categoryList.add(new CategoryModel("Weekly Top", "Most played"));
        categoryList.add(new CategoryModel("Energy Mix", "High tempo"));
        categoryList.add(new CategoryModel("Chill", "Relax vibes"));
    }

    private void setupSongRecycler() {
        recyclerSongs.setLayoutManager(new LinearLayoutManager(this));
        songList = new ArrayList<>();
        songAdapter = new SongAdapter(this, songList);
        recyclerSongs.setAdapter(songAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        songList.clear();
        songList.addAll(FavouriteManager.getFavouriteSongs());
        songAdapter.notifyDataSetChanged();
    }
}
