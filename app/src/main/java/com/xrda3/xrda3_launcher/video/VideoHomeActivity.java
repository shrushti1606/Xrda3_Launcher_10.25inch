package com.xrda3.xrda3_launcher.video;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.xrda3.xrda3_launcher.R;

public class VideoHomeActivity extends AppCompatActivity {

    // ================= Sidebar buttons =================
    private ImageButton btnHome, btnVideo, btnMusic, btnFolder, btnFavorites, btnVideoPlayer;

    // ================= Continue Watching boxes =================
    private LinearLayout box1, box2, box3, box4, box5, box6;

    // ================= Search Box =================
    private EditText searchBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videohome);

        // ================= Search Box Init =================
        searchBox = findViewById(R.id.search_box);

        // ================= Sidebar buttons init =================
        btnHome = findViewById(R.id.btnhome);
        btnVideoPlayer = findViewById(R.id.btnvideoplayer);
        btnVideo = findViewById(R.id.btnvideo);   // XML me ye id hona chahiye
        btnMusic = findViewById(R.id.btnmusic);
        btnFolder = findViewById(R.id.btnfolder);
        btnFavorites = findViewById(R.id.btnHeartSidebar);

        // ================= Sidebar click actions =================
        btnHome.setOnClickListener(v -> finish());

        btnVideoPlayer.setOnClickListener(v ->
                startActivity(new Intent(this, ContinueWatchingActivity.class))
        );

        btnVideo.setOnClickListener(v ->
                startActivity(new Intent(this, AllVideosActivity.class))
        );

        btnMusic.setOnClickListener(v ->
                startActivity(new Intent(this, Music_Activity.class))
        );

        btnFolder.setOnClickListener(v ->
                startActivity(new Intent(this, BrowseFoldersActivity.class))
        );

        btnFavorites.setOnClickListener(v ->
                startActivity(new Intent(this, FavoritesActivity.class))
        );

        // ================= Continue Watching boxes init =================
        box1 = findViewById(R.id.box1);
        box2 = findViewById(R.id.box2);
        box3 = findViewById(R.id.box3);
        box4 = findViewById(R.id.box4);
        box5 = findViewById(R.id.box5);
        box6 = findViewById(R.id.box6);

        // ================= Continue Watching clicks =================
        box1.setOnClickListener(v ->
                openVideo(R.raw.whatsapp_video1, "Beach Top View"));

        box2.setOnClickListener(v ->
                openVideo(R.raw.this_valentine_s_day, "Kidntoons"));

        box3.setOnClickListener(v ->
                openVideo(R.raw.epic_battle, "Giggly Groove"));

        box4.setOnClickListener(v ->
                openVideo(R.raw.beautiful_world, "Azzatto.ai"));

        box5.setOnClickListener(v ->
                openVideo(R.raw.traffic_traffic, "The Meow Meow"));

        box6.setOnClickListener(v ->
                openVideo(R.raw.beach_video, "Shashwat Sachdev"));

        // ================= SEARCH LISTENER (Aapka diya hua code) =================
        searchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterVideos(s.toString());   // 👈 Search call
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    // ================= Filter Method =================
    private void filterVideos(String text) {

        text = text.toLowerCase();

        if (text.isEmpty()) {
            box1.setVisibility(View.VISIBLE);
            box2.setVisibility(View.VISIBLE);
            box3.setVisibility(View.VISIBLE);
            box4.setVisibility(View.VISIBLE);
            box5.setVisibility(View.VISIBLE);
            box6.setVisibility(View.VISIBLE);
            return;
        }

        box1.setVisibility("beach top view".contains(text) ? View.VISIBLE : View.GONE);
        box2.setVisibility("kidntoons".contains(text) ? View.VISIBLE : View.GONE);
        box3.setVisibility("giggly groove".contains(text) ? View.VISIBLE : View.GONE);
        box4.setVisibility("azzatto.ai".contains(text) ? View.VISIBLE : View.GONE);
        box5.setVisibility("the meow meow".contains(text) ? View.VISIBLE : View.GONE);
        box6.setVisibility("shashwat sachdev".contains(text) ? View.VISIBLE : View.GONE);
    }

    // ================= Open Video Player =================
    private void openVideo(int videoResId, String title) {
        Intent intent = new Intent(VideoHomeActivity.this, VideoPlayerActivity.class);
        intent.putExtra("videoResId", videoResId);
        intent.putExtra("videoTitle", title);
        startActivity(intent);
    }
}
