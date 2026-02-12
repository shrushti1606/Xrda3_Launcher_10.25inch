package com.xrda3.xrda3_launcher.video;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.xrda3.xrda3_launcher.R;

public class BrowseFoldersActivity extends AppCompatActivity {

    private EditText searchBox;
    private LinearLayout cameraRollBox, downloadsBox, moviesBox, musicVideosBox, dashcamBox, favoriteBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.brows_folder_activity); // XML layout file

        // Initialize views
        searchBox = findViewById(R.id.searchBox);
        cameraRollBox = findViewById(R.id.camerarollbox);
        downloadsBox = findViewById(R.id.downloadbox);
        moviesBox = findViewById(R.id.moviesBox);
        musicVideosBox = findViewById(R.id.musicbox);
        dashcamBox = findViewById(R.id.dashcamBox);
        favoriteBox = findViewById(R.id.favoritrbox);

        // Set click listeners for each folder box
        cameraRollBox.setOnClickListener(v -> openFolder("Camera Roll"));
        downloadsBox.setOnClickListener(v -> openFolder("Downloads"));
        moviesBox.setOnClickListener(v -> openFolder("Movies"));
        musicVideosBox.setOnClickListener(v -> openFolder("Music Videos"));
        dashcamBox.setOnClickListener(v -> openFolder("Dashcam"));
        favoriteBox.setOnClickListener(v -> openFolder("Favorite"));

        // ================= Search Functionality =================
        searchBox.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                String query = s.toString().toLowerCase();

                cameraRollBox.setVisibility("camera roll".contains(query) ? View.VISIBLE : View.GONE);
                downloadsBox.setVisibility("downloads".contains(query) ? View.VISIBLE : View.GONE);
                moviesBox.setVisibility("movies".contains(query) ? View.VISIBLE : View.GONE);
                musicVideosBox.setVisibility("music videos".contains(query) ? View.VISIBLE : View.GONE);
                dashcamBox.setVisibility("dashcam".contains(query) ? View.VISIBLE : View.GONE);
                favoriteBox.setVisibility("favorite".contains(query) ? View.VISIBLE : View.GONE);
            }
        });
    }

    // Function to open FolderActivity with folder name
    private void openFolder(String folderName) {
        Intent intent = new Intent(BrowseFoldersActivity.this, FolderActivity.class);
        intent.putExtra("folderName", folderName);
        startActivity(intent);
    }
}
