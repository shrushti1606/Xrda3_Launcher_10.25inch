package com.xrda3.xrda3_launcher.video;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.xrda3.xrda3_launcher.R;

public class VideoHomeActivity extends AppCompatActivity {

    // ================= Sidebar buttons =================
    private ImageButton btnHome, btnVideo, btnMusic, btnFolder, btnFavorites, btnVideoPlayer;

    // ================= Continue Watching boxes =================
    private LinearLayout box1, box2, box3, box4, box5, box6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videohome);

        // ================= Sidebar buttons init =================
        btnHome = findViewById(R.id.btnhome);
        btnVideoPlayer = findViewById(R.id.btnvideoplayer);
        btnVideo = findViewById(R.id.btnvideo);
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
                openVideo(R.raw.whatsapp_video5, "Kidntoons"));

        box3.setOnClickListener(v ->
                openVideo(R.raw.whatsapp_video3, "Giggly.Groove"));

        box4.setOnClickListener(v ->
                openVideo(R.raw.whatsapp_video4, "Azzatto.ai"));

        box5.setOnClickListener(v ->
                openVideo(R.raw.whatsapp_video6, "The.Meow.Meow"));

        box6.setOnClickListener(v ->
                openVideo(R.raw.whatsapp_video2, "Shashwat Sachdev"));
    }

    // ================= Open Video Player =================
    private void openVideo(int videoResId, String title) {
        Intent intent = new Intent(com.xrda3.xrda3_launcher.video.VideoHomeActivity.this, VideoPlayerActivity.class);
        intent.putExtra("videoResId", videoResId);
        intent.putExtra("videoTitle", title);
        startActivity(intent);
    }
}
