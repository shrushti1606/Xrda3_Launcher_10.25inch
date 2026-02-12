package com.xrda3.xrda3_launcher.video;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.xrda3.xrda3_launcher.R;

import java.util.Map;

public class ContinueWatchingActivity extends AppCompatActivity {

    private LinearLayout videoListContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scroll_activity_allvideos);
        // ✅ same UI, no XML change

        videoListContainer = findViewById(R.id.videoListContainer);

        loadContinueWatchingVideos();
    }

    private void loadContinueWatchingVideos() {
        SharedPreferences prefs = getSharedPreferences("continue_watching", MODE_PRIVATE);
        Map<String, ?> allVideos = prefs.getAll();

        if (allVideos.isEmpty()) {
            TextView empty = new TextView(this);
            empty.setText("No videos in Continue Watching");
            empty.setTextColor(0xFFFFFFFF);
            empty.setTextSize(18);
            videoListContainer.addView(empty);
            return;
        }

        for (Map.Entry<String, ?> entry : allVideos.entrySet()) {
            String title = entry.getKey();
            int resumePosition = (int) entry.getValue();

            addVideoCard(title, resumePosition);
        }
    }

    // ================= HORIZONTAL CARD =================
    private void addVideoCard(String title, int resumePosition) {

        // Parent horizontal layout
        LinearLayout card = new LinearLayout(this);
        card.setOrientation(LinearLayout.HORIZONTAL);
        card.setPadding(20, 20, 20, 20);
        card.setGravity(Gravity.CENTER_VERTICAL);

        LinearLayout.LayoutParams cardParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        cardParams.bottomMargin = 20;
        card.setLayoutParams(cardParams);

        // ===== Cover Image =====
        ImageView cover = new ImageView(this);
        cover.setImageResource(getCoverByTitle(title));
        LinearLayout.LayoutParams coverParams =
                new LinearLayout.LayoutParams(250, 150);
        coverParams.rightMargin = 20;
        cover.setLayoutParams(coverParams);
        cover.setScaleType(ImageView.ScaleType.CENTER_CROP);

        // ===== Title =====
        TextView text = new TextView(this);
        text.setText(title);
        text.setTextColor(0xFFFFFFFF);
        text.setTextSize(18);

        // Add views
        card.addView(cover);
        card.addView(text);

        // Click → resume video
        card.setOnClickListener(v -> {
            Intent intent = new Intent(this, VideoPlayerActivity.class);
            intent.putExtra("videoResId", getVideoResIdByTitle(title));
            intent.putExtra("videoTitle", title);
            intent.putExtra("resumePosition", resumePosition);
            startActivity(intent);
        });

        videoListContainer.addView(card);
    }

    // ================= HELPERS =================
    private int getVideoResIdByTitle(String title) {
        switch (title) {
            case "Beach Top View": return R.raw.whatsapp_video1;
            case "Kidntoons": return R.raw.this_valentine_s_day;
            case "Giggly.Groove": return R.raw.epic_battle;
            case "Azzatto.ai": return R.raw.beautiful_world;
            case "The.Meow.Meow": return R.raw.traffic_traffic;
            case "Shashwat Sachdev": return R.raw.beach_video;
            default: return R.raw.whatsapp_video1;
        }
    }

    private int getCoverByTitle(String title) {
        switch (title) {
            case "Beach Top View": return R.drawable.cover_image1;
            case "Kidntoons": return R.drawable.penguin_image;
            case "Giggly.Groove": return R.drawable.jangal_image;
            case "Azzatto.ai": return R.drawable.beautiful_world_image;
            case "The.Meow.Meow": return R.drawable.traffic_image;
            case "Shashwat Sachdev": return R.drawable.beach_image;
            default: return R.drawable.cover_image1;
        }
    }
}
