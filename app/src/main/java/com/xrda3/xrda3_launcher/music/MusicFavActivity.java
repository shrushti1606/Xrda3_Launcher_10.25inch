package com.xrda3.xrda3_launcher.music;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xrda3.xrda3_launcher.R;
import com.xrda3.xrda3_launcher.music.adapter.FavCategoryAdapter;
import com.xrda3.xrda3_launcher.music.adapter.SongAdapter;
import com.xrda3.xrda3_launcher.music.model.FavCategoryModel;
import com.xrda3.xrda3_launcher.music.model.SongModel;

import java.util.ArrayList;

public class MusicFavActivity extends AppCompatActivity {

    private RecyclerView recyclerCategories, recyclerSongs;
    private FavCategoryAdapter categoryAdapter;
    private SongAdapter songAdapter;
    ImageView click_home, click_menu, click_down, click_set, click_heart;

    private ArrayList<FavCategoryModel> categoryList;
    private ArrayList<SongModel> songList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_fav);

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
        categoryAdapter = new FavCategoryAdapter(categoryList);
        recyclerCategories.setAdapter(categoryAdapter);
    }

    private void setupCategoryData() {
        categoryList = new ArrayList<>();
        categoryList.add(new FavCategoryModel("On Repeat", "Songs you love"));
        categoryList.add(new FavCategoryModel("Weekly Top", "Most played"));
        categoryList.add(new FavCategoryModel("Energy Mix", "High tempo"));
        categoryList.add(new FavCategoryModel("Chill", "Relax vibes"));
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
