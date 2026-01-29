package com.xrda3.xrda3_launcher.music;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xrda3.xrda3_launcher.R;
import com.xrda3.xrda3_launcher.music.adapter.JumpBackInAdapter;
import com.xrda3.xrda3_launcher.music.model.JumpBackInModel;

import java.util.ArrayList;

public class MusicHomeActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ConstraintLayout vibeRelax, vibeWorkout, vibeHappy, vibeChill;
    EditText searchEditText;
    RecyclerView recyclerJumpBackIn;
    ImageView  click_home,click_menu,click_library,click_set, click_heart;

    JumpBackInAdapter adapter;
    ArrayList<JumpBackInModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_home);

        click_home = findViewById(R.id.click_home);
        click_menu = findViewById(R.id.click_menu);
        click_library = findViewById(R.id.click_library);
        click_set = findViewById(R.id.click_set);
        click_heart = findViewById(R.id.click_heart);




        searchEditText = findViewById(R.id.searchEditText);
        recyclerJumpBackIn = findViewById(R.id.recyclerJumpBackIn);
        recyclerJumpBackIn.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        );

        list = new ArrayList<>();
        list.add(new JumpBackInModel("Liked Songs", R.drawable.likedsongs));
        list.add(new JumpBackInModel("Daily Mix 1", R.drawable.midnightdrive));
        list.add(new JumpBackInModel("Workout Hits", R.drawable.morningvibes));
        list.add(new JumpBackInModel("Chill Vibes", R.drawable.chillvibes));
        list.add(new JumpBackInModel("Top 2025", R.drawable.tophits));

        adapter = new JumpBackInAdapter(this, list);
        recyclerJumpBackIn.setAdapter(adapter);


        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void afterTextChanged(Editable s) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.filter(s.toString());
            }
        });

        findViewById(R.id.click_library).setOnClickListener(v -> {
            Intent intent = new Intent(MusicHomeActivity.this, MusicLibraryActivity.class);
            startActivity(intent);
        });
        findViewById(R.id.click_set).setOnClickListener(v -> {
            Intent intent = new Intent(MusicHomeActivity.this, MusicSettingsActivity.class);
            startActivity(intent);
        });
        findViewById(R.id.click_heart).setOnClickListener(v -> {
            Intent intent = new Intent(MusicHomeActivity.this, MusicFavActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.vibeRelax).setOnClickListener(v -> {
            Intent intent = new Intent(MusicHomeActivity.this, VibeSongsActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.vibeWorkout).setOnClickListener(v -> {
            Intent intent = new Intent(MusicHomeActivity.this, WorkoutSongActivity.class);
            startActivity(intent);
        });
        findViewById(R.id.vibeHappy).setOnClickListener(v -> {
            Intent intent = new Intent(MusicHomeActivity.this, HappySongsActivity.class);
            startActivity(intent);
        });
        findViewById(R.id.vibeChill).setOnClickListener(v -> {
            Intent intent = new Intent(MusicHomeActivity.this, ChillSongsActivity.class);
            startActivity(intent);
        });

    }
}
