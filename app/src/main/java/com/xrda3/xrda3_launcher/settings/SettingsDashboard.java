package com.xrda3.xrda3_launcher.settings;

import android.app.NotificationManager;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.xrda3.xrda3_launcher.AppsActivity;
import com.xrda3.xrda3_launcher.R;
import com.xrda3.xrda3_launcher.music.PermissionActivity;

public class SettingsDashboard extends AppCompatActivity {

    private FrameLayout cardSystem, cardDisplay, cardTime, cardDnd, cardBluetooth, cardWifi, cardCallFeatures;

    // Toggle indicators
    private View toggleSystem, toggleDisplay, toggleTime,
            toggleDnd, toggleBluetooth, toggleWifi, toggleCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_dashboard);

        initViews();

        cardSystem = findViewById(R.id.cardSystem);
        cardSystem.setOnClickListener(view -> {
            Intent intent = new Intent(SettingsDashboard.this, SystemInformation.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
        });

        cardDisplay = findViewById(R.id.cardDisplay);
        cardDisplay.setOnClickListener(view -> {
            Intent intent = new Intent(SettingsDashboard.this, DisplaySettings.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
        });

        cardTime = findViewById(R.id.cardTime);
        cardTime.setOnClickListener(view -> {
            Intent intent = new Intent(SettingsDashboard.this, TimeSettings.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
        });

        cardDnd = findViewById(R.id.cardDnd);
        cardDnd.setOnClickListener(view -> {
            Intent intent = new Intent(SettingsDashboard.this, DNDSettings.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
        });

        cardBluetooth = findViewById(R.id.cardBluetooth);
        cardBluetooth.setOnClickListener(view -> {
            Intent intent = new Intent(SettingsDashboard.this, Bluetooth.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
        });

        cardWifi = findViewById(R.id.cardWifi);
        cardWifi.setOnClickListener(view -> {
            Intent intent = new Intent(SettingsDashboard.this, Wifi.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
        });

        cardCallFeatures = findViewById(R.id.cardCallFeatures);
        cardCallFeatures.setOnClickListener(view -> {
            Intent intent = new Intent(SettingsDashboard.this, CallFeatures.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
        });
    }

    private void initViews() {
        toggleSystem = findViewById(R.id.toggleSystem);
        toggleDisplay = findViewById(R.id.toggleDisplay);
        toggleTime = findViewById(R.id.toggleTime);
        toggleDnd = findViewById(R.id.toggleDnd);
        toggleBluetooth = findViewById(R.id.toggleBluetooth);
        toggleWifi = findViewById(R.id.toggleWifi);
        toggleCall = findViewById(R.id.toggleCall);
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateAllToggles();
    }

    private void updateAllToggles() {
        updateBluetoothState();
        updateWifiState();
        updateDndState();
        updateDisplayState();
        updateTimeState();
        updateCallState();
        updateSystemState();
    }

    // ================= INDIVIDUAL STATES =================

    private void updateBluetoothState() {
        BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
        boolean enabled = adapter != null && adapter.isEnabled();
        setToggle(toggleBluetooth, enabled);
    }

    private void updateWifiState() {
        if (checkSelfPermission(android.Manifest.permission.ACCESS_WIFI_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            return; // Permission missing, don’t crash
        }

        WifiManager wifiManager =
                (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);

        if (wifiManager != null) {
            boolean enabled = wifiManager.isWifiEnabled();
            setToggle(toggleWifi, enabled);
        }
    }


    private void updateDndState() {
        NotificationManager nm =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        boolean enabled = nm != null &&
                nm.getCurrentInterruptionFilter()
                        != NotificationManager.INTERRUPTION_FILTER_ALL;

        setToggle(toggleDnd, enabled);
    }

    // App-level settings (SharedPreferences)
    private void updateDisplayState() {
        SharedPreferences prefs =
                getSharedPreferences("settings", MODE_PRIVATE);
        boolean darkMode = prefs.getBoolean("dark_mode", false);
        setToggle(toggleDisplay, darkMode);
    }

    private void updateTimeState() {
        SharedPreferences prefs =
                getSharedPreferences("settings", MODE_PRIVATE);
        boolean is24Hour = prefs.getBoolean("time_24h", false);
        setToggle(toggleTime, is24Hour);
    }

    private void updateCallState() {
        SharedPreferences prefs =
                getSharedPreferences("settings", MODE_PRIVATE);
        boolean autoAnswer = prefs.getBoolean("auto_answer", false);
        setToggle(toggleCall, autoAnswer);
    }

    // Always active (informational screen)
    private void updateSystemState() {
        setToggle(toggleSystem, true);
    }

    // ================= UI HELPER =================
    private void setToggle(View toggle, boolean active) {
        if (toggle == null) return;

        toggle.setBackgroundResource(
                active
                        ? R.drawable.bg_toggle_active   // sky blue
                        : R.drawable.bg_toggle_inactive // grey
        );
    }
}