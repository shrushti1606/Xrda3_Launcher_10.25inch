package com.xrda3.xrda3_launcher.video;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.xrda3.xrda3_launcher.R;

public class Music_Activity extends AppCompatActivity {

    private LinearLayout musicContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        musicContainer = findViewById(R.id.musicContainer);

        loadMusicList();
    }

    private void loadMusicList() {
        // Sample music titles, आप अपने music resources से replace कर सकते हैं
        String[] musicTitles = {"Song 1", "Song 2", "Song 3", "Song 4", "Song 5"};

        for (String title : musicTitles) {
            TextView musicItem = new TextView(this);
            musicItem.setText(title);
            musicItem.setTextSize(18);
            musicItem.setTextColor(0xFFFFFFFF);
            musicItem.setPadding(20, 20, 20, 20);

            musicItem.setOnClickListener(v -> {
                // Music click action
                // अगर music play करना है तो intent से MusicPlayerActivity खोल सकते हैं
            });

            musicContainer.addView(musicItem);
        }
    }
}

