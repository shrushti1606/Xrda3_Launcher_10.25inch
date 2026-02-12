package com.xrda3.xrda3_launcher.music;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import com.xrda3.xrda3_launcher.R;

public class MusicSettingsActivity extends AppCompatActivity {

    Spinner spinnerStreaming, spinnerDownload;
    Switch switchNormalize;
    ImageView click_menu, click_home, click_down, click_set, click_heart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_settings);

        click_home = findViewById(R.id.click_home);
        click_down = findViewById(R.id.click_down);
        click_set = findViewById(R.id.click_set);
        click_heart = findViewById(R.id.click_heart);
        click_home.setOnClickListener(v ->
                startActivity(new Intent(this, MusicHomeActivity.class)));


        click_down.setOnClickListener(v ->
                startActivity(new Intent(this, MusicDownloadActivity.class)));

        click_set.setOnClickListener(v ->
                startActivity(new Intent(this, MusicSettingsActivity.class)));

        click_heart.setOnClickListener(v ->
                startActivity(new Intent(this, MusicFavActivity.class)));


        spinnerStreaming = findViewById(R.id.spinnerStreaming);
        spinnerDownload = findViewById(R.id.spinnerDownload);
        switchNormalize = findViewById(R.id.switchNormalize);

        String[] streamingQuality = {"Low", "Medium", "High (320kbps)"};
        String[] downloadQuality = {"Medium", "High", "Very High"};

        spinnerStreaming.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                streamingQuality));

        spinnerDownload.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                downloadQuality));
    }
}
