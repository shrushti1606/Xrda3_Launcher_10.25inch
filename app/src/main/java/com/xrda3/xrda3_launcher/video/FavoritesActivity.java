package com.xrda3.xrda3_launcher.video;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.xrda3.xrda3_launcher.R;

import java.util.Set;

public class FavoritesActivity extends AppCompatActivity {

    private LinearLayout favoritesListContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorites_list);

        favoritesListContainer = findViewById(R.id.favoritesVerticalContainer);
        loadFavorites(); // Load favorites on start
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadFavorites(); // Refresh favorites
    }

    private void loadFavorites() {
        favoritesListContainer.removeAllViews();

        Set<String> favorites = FavoritesManager.getFavorites(this);

        if (favorites != null && !favorites.isEmpty()) {
            for (String title : favorites) {
                LinearLayout card = createFavoriteCard(title);
                favoritesListContainer.addView(card);
            }
        } else {
            TextView emptyText = new TextView(this);
            emptyText.setText("No favorites yet!");
            emptyText.setTextColor(0xFFFFFFFF);
            emptyText.setTextSize(18);
            favoritesListContainer.addView(emptyText);
        }
    }

    private LinearLayout createFavoriteCard(String title) {
        LinearLayout card = new LinearLayout(this);
        card.setOrientation(LinearLayout.HORIZONTAL); // Horizontal inside vertical list
        card.setPadding(12, 12, 12, 12);
        card.setGravity(Gravity.CENTER_VERTICAL);

        // Cover Image
        ImageView cover = new ImageView(this);
        cover.setImageResource(getCoverByTitle(title));
        LinearLayout.LayoutParams coverParams = new LinearLayout.LayoutParams(200, 200); // medium box
        coverParams.rightMargin = 12;
        cover.setLayoutParams(coverParams);
        cover.setScaleType(ImageView.ScaleType.CENTER_CROP);

        // Title
        TextView text = new TextView(this);
        text.setText(title);
        text.setTextColor(0xFFFFFFFF);
        text.setTextSize(18);
        LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        text.setLayoutParams(textParams);

        card.addView(cover);
        card.addView(text);

        // Card click → play video
        card.setOnClickListener(v -> {
            Intent intent = new Intent(FavoritesActivity.this, VideoPlayerActivity.class);
            intent.putExtra("videoResId", getVideoResIdByTitle(title));
            intent.putExtra("videoTitle", title);
            startActivity(intent);
        });

        LinearLayout.LayoutParams cardParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        cardParams.bottomMargin = 12;
        card.setLayoutParams(cardParams);

        return card;
    }

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
