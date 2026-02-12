package com.xrda3.xrda3_launcher.video;

import android.content.Intent;
import android.os.Bundle;
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
        cameraRollBox = findViewById(R.id.cameraRollBox);
        downloadsBox = findViewById(R.id.downloadsBox);
        moviesBox = findViewById(R.id.moviesBox);
        musicVideosBox = findViewById(R.id.musicVideosBox);
        dashcamBox = findViewById(R.id.dashcamBox);
        favoriteBox = findViewById(R.id.favoriteBox);

        // Set click listeners for each folder box
        cameraRollBox.setOnClickListener(v -> openFolder("Camera Roll"));
        downloadsBox.setOnClickListener(v -> openFolder("Downloads"));
        moviesBox.setOnClickListener(v -> openFolder("Movies"));
        musicVideosBox.setOnClickListener(v -> openFolder("Music Videos"));
        dashcamBox.setOnClickListener(v -> openFolder("Dashcam"));
        favoriteBox.setOnClickListener(v -> openFolder("Favorite"));
    }

    // Function to open FolderActivity with folder name
    private void openFolder(String folderName) {
        Intent intent = new Intent(BrowseFoldersActivity.this, FolderActivity.class);
        intent.putExtra("folderName", folderName);
        startActivity(intent);
    }
}
