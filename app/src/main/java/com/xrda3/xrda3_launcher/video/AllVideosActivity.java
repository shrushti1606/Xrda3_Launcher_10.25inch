package com.xrda3.xrda3_launcher.video;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.xrda3.xrda3_launcher.R;

public class AllVideosActivity extends AppCompatActivity {

    private LinearLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scroll_activity_allvideos);

        container = findViewById(R.id.videoListContainer);

        addVideo("Beach Top View", R.raw.whatsapp_video1, R.drawable.cover_image1);
        addVideo("Kidntoons", R.raw.this_valentine_s_day, R.drawable.penguin_image);
        addVideo("Giggly.Groove", R.raw.epic_battle, R.drawable.jangal_image);
        addVideo("Azzatto.ai", R.raw.beautiful_world, R.drawable.beautiful_world_image);
        addVideo("The.Meow.Meow", R.raw.traffic_traffic, R.drawable.traffic_image);
        addVideo("Shashwat Sachdev", R.raw.beach_video, R.drawable.beach_image);
    }

    private void addVideo(String title, int videoResId, int coverResId) {
        LinearLayout card = new LinearLayout(this);
        card.setOrientation(LinearLayout.HORIZONTAL);
        card.setPadding(20, 20, 20, 20);
        card.setGravity(Gravity.CENTER_VERTICAL);
        card.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));

        // Cover image
        ImageView cover = new ImageView(this);
        cover.setImageResource(coverResId);
        LinearLayout.LayoutParams coverParams = new LinearLayout.LayoutParams(250, 150);
        coverParams.rightMargin = 20;
        cover.setLayoutParams(coverParams);
        cover.setScaleType(ImageView.ScaleType.CENTER_CROP);

        // Title text
        TextView text = new TextView(this);
        text.setText(title);
        text.setTextSize(18);
        text.setTextColor(0xFFFFFFFF);
        LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(
                0,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                1f
        );
        text.setLayoutParams(textParams);

        // Like button
        ImageButton likeBtn = new ImageButton(this);
        likeBtn.setBackgroundColor(0x00000000);
        likeBtn.setImageResource(FavoritesManager.isFavorite(this, title) ?
                R.drawable.heart_filled : R.drawable.heart_outline);
        LinearLayout.LayoutParams btnParams = new LinearLayout.LayoutParams(80, 80);
        likeBtn.setLayoutParams(btnParams);

        likeBtn.setOnClickListener(v -> {
            if (FavoritesManager.isFavorite(this, title)) {
                FavoritesManager.removeFavorite(this, title);
                likeBtn.setImageResource(R.drawable.heart_outline);
            } else {
                FavoritesManager.addFavorite(this, title);
                likeBtn.setImageResource(R.drawable.heart_filled);
            }
        });

        card.addView(cover);
        card.addView(text);
        card.addView(likeBtn);

        // Card click → play video
        card.setOnClickListener(v -> {
            Intent intent = new Intent(AllVideosActivity.this, VideoPlayerActivity.class);
            intent.putExtra("videoResId", videoResId);
            intent.putExtra("videoTitle", title);
            startActivity(intent);
        });

        LinearLayout.LayoutParams cardParams = (LinearLayout.LayoutParams) card.getLayoutParams();
        cardParams.setMargins(0, 0, 0, 20);
        card.setLayoutParams(cardParams);

        container.addView(card);
    }
}
