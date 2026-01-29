package com.xrda3.xrda3_launcher;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.xrda3.xrda3_launcher.call.CallSplashActivity;
import com.xrda3.xrda3_launcher.car.CarDashboard;
import com.xrda3.xrda3_launcher.contacts.Contacts;
import com.xrda3.xrda3_launcher.maps.MapsDashboard;
import com.xrda3.xrda3_launcher.music.PermissionActivity;
import com.xrda3.xrda3_launcher.settings.SettingsDashboard;
import com.xrda3.xrda3_launcher.video.VideoHomeActivity;

public class AppsActivity extends AppCompatActivity {

    private View callScreen;
    private View musicScreen;
    private View contactsScreen;
    private View carScreen;
    private View videoScreen;
    private View settingsScreen;
    private View mapScreen ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apps);

        callScreen = findViewById(R.id.call);
        callScreen.setOnClickListener(view -> {
            Intent intent = new Intent(AppsActivity.this, CallSplashActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
        });

        musicScreen = findViewById(R.id.music);
        musicScreen.setOnClickListener(view -> {
            Intent intent = new Intent(AppsActivity.this, PermissionActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
        });

        contactsScreen = findViewById(R.id.contacts);
        contactsScreen.setOnClickListener(view -> {
            Intent intent = new Intent(AppsActivity.this, Contacts.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
        });

        carScreen = findViewById(R.id.car);
        carScreen.setOnClickListener(view -> {
            Intent intent = new Intent(AppsActivity.this, CarDashboard.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
        });

        videoScreen = findViewById(R.id.video);
        videoScreen.setOnClickListener(view -> {
            Intent intent = new Intent(AppsActivity.this, VideoHomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
        });

        settingsScreen = findViewById(R.id.settings);
        settingsScreen.setOnClickListener(view -> {
            Intent intent = new Intent(AppsActivity.this, SettingsDashboard.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
        });

        mapScreen = findViewById(R.id.map);
        mapScreen.setOnClickListener(view -> {
            Intent intent = new Intent(AppsActivity.this, MapsDashboard.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
        });
    }
}