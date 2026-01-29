package com.xrda3.xrda3_launcher.music;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;

import com.xrda3.xrda3_launcher.R;
import com.xrda3.xrda3_launcher.music.MusicHomeActivity;

public class MusicSplashActivity extends AppCompatActivity {

    private static final int SPLASH_TIME = 2500; // 2.5 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_splash);

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            Intent intent = new Intent(MusicSplashActivity.this, MusicHomeActivity.class);
            startActivity(intent);
            finish();
        }, SPLASH_TIME);
    }
}
