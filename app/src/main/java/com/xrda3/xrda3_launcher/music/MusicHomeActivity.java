package com.xrda3.xrda3_launcher.music;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xrda3.xrda3_launcher.R;
import com.xrda3.xrda3_launcher.music.adapter.JumpBackInAdapter;
import com.xrda3.xrda3_launcher.music.model.JumpBackInModel;

import java.util.ArrayList;

public class MusicHomeActivity extends AppCompatActivity {

    ConstraintLayout vibeRelax, vibeWorkout, vibeHappy, vibeChill, constraintlayout;
    EditText searchEditText;
    RecyclerView recyclerJumpBackIn;

    ImageView click_home, click_menu, click_down, click_set, click_heart;

    JumpBackInAdapter adapter;
    ArrayList<JumpBackInModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_home);
        constraintlayout = findViewById(R.id.constraintlayout);

        click_menu = findViewById(R.id.click_menu);
        click_home = findViewById(R.id.click_home);
        click_down = findViewById(R.id.click_down);
        click_set = findViewById(R.id.click_set);
        click_heart = findViewById(R.id.click_heart);

        vibeRelax = findViewById(R.id.vibeRelax);
        vibeWorkout = findViewById(R.id.vibeWorkout);
        vibeHappy = findViewById(R.id.vibeHappy);
        vibeChill = findViewById(R.id.vibeChill);

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
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

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

        vibeRelax.setOnClickListener(v ->
                startActivity(new Intent(this, VibeSongsActivity.class)));

        vibeWorkout.setOnClickListener(v ->
                startActivity(new Intent(this, WorkoutSongActivity.class)));

        vibeHappy.setOnClickListener(v ->
                startActivity(new Intent(this, HappySongsActivity.class)));

        vibeChill.setOnClickListener(v ->
                startActivity(new Intent(this, ChillSongsActivity.class)));
    }

    @SuppressLint("GestureBackNavigation")
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
