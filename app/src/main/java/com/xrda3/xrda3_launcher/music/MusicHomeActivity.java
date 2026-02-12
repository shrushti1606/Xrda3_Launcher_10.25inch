package com.xrda3.xrda3_launcher.music;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Build;
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
import com.xrda3.xrda3_launcher.music.adapter.OnJumpBackClickListener;
import com.xrda3.xrda3_launcher.music.model.JumpBackInModel;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MusicHomeActivity extends AppCompatActivity {

    ConstraintLayout vibeRelax, vibeWorkout, vibeHappy, vibeChill;
    EditText searchEditText;
    RecyclerView recyclerJumpBackIn;

    ImageView click_home, click_down, click_set, click_heart;

    JumpBackInAdapter adapter;
    List<JumpBackInModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_home);

        // --- Initialize views ---
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

        // --- RecyclerView setup ---
        recyclerJumpBackIn.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        );
        recyclerJumpBackIn.setHasFixedSize(true);

        // --- Initialize list ---
        list = new ArrayList<>();
        list.add(new JumpBackInModel("Liked Songs", R.drawable.likedsongs, "LIKED"));
        list.add(new JumpBackInModel("Daily Mix 1", R.drawable.daily_mix, "DAILY"));
        list.add(new JumpBackInModel("Workout Hits", R.drawable.workout_img, "WORKOUT"));
        list.add(new JumpBackInModel("Chill Vibes", R.drawable.chill_img, "CHILL"));
        list.add(new JumpBackInModel("Top 2025", R.drawable.top_img, "TOP"));

        // --- Load songs from pen drive ---
        if (checkStoragePermission()) {
            list.addAll(loadSongsFromPenDrive());
        }

        // --- Setup adapter ---
        adapter = new JumpBackInAdapter(this, list, new OnJumpBackClickListener() {
            @Override
            public void onCardClick(JumpBackInModel item) {
                handleItemClick(item);
            }

            @Override
            public void onImageClick(JumpBackInModel item) {
                handleItemClick(item);
            }
        });

        recyclerJumpBackIn.setAdapter(adapter);

        // --- Search filter ---
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void afterTextChanged(Editable s) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.filter(s.toString());
            }
        });

        // --- Category click listeners ---
        vibeRelax.setOnClickListener(v -> startActivity(new Intent(this, RelaxSongsActivity.class)));
        vibeWorkout.setOnClickListener(v -> startActivity(new Intent(this, WorkoutSongActivity.class)));
        vibeHappy.setOnClickListener(v -> startActivity(new Intent(this, HappySongsActivity.class)));
        vibeChill.setOnClickListener(v -> startActivity(new Intent(this, ChillSongsActivity.class)));
    }

    // --- Handle click on items ---
    private void handleItemClick(JumpBackInModel item) {
        if (item.getPageType().startsWith("SONG_PATH:")) {
            String path = item.getPageType().replace("SONG_PATH:", "");
            Intent intent = new Intent(this, MusicPlayerActivity.class);
            intent.putExtra("SONG_PATH", path);
            startActivity(intent);
        } else {
            openPage(item);
        }
    }

    // --- Open categories ---
    private void openPage(JumpBackInModel item) {
        Intent intent;

        switch (item.getPageType()) {
            case "LIKED": intent = new Intent(this, MusicFavActivity.class); break;
            case "WORKOUT": intent = new Intent(this, WorkoutSongActivity.class); break;
            case "CHILL": intent = new Intent(this, ChillSongsActivity.class); break;
            case "DAILY": intent = new Intent(this, HappySongsActivity.class); break;
            case "TOP": intent = new Intent(this, ChillSongsActivity.class); break;
            default: intent = new Intent(this, JumpBackInDetailActivity.class);
        }

        intent.putExtra("TITLE", item.getTitle());
        intent.putExtra("IMAGE", item.getImageRes());
        startActivity(intent);
    }

    // --- Load songs from pen drive ---
    private List<JumpBackInModel> loadSongsFromPenDrive() {
        List<JumpBackInModel> songs = new ArrayList<>();
        File usbDir = new File("/storage/USB1/"); // Update if your pen drive path is different

        if (usbDir.exists() && usbDir.isDirectory()) {
            File[] files = usbDir.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.getName().endsWith(".mp3") || file.getName().endsWith(".wav")) {
                        songs.add(new JumpBackInModel(
                                file.getName(),
                                R.drawable.music_placeholder,   // default placeholder
                                "SONG_PATH:" + file.getAbsolutePath()
                        ));
                    }
                }
                // Sort descending by name
                Collections.sort(songs, (f1, f2) -> f2.getTitle().compareTo(f1.getTitle()));
            }
        }
        return songs;
    }

    // --- Check storage permission ---
    private boolean checkStoragePermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 101);
                return false;
            }
        }
        return true;
    }

    @SuppressLint("GestureBackNavigation")
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
