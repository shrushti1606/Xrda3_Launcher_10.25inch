package com.xrda3.xrda3_launcher.music;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import com.xrda3.xrda3_launcher.R;

public class MusicSettingsActivity extends AppCompatActivity {

    Spinner spinnerStreaming, spinnerDownload;
    Switch switchNormalize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_settings);

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
