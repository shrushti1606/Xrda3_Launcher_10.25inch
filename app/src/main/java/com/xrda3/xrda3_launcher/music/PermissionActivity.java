package com.xrda3.xrda3_launcher.music;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.xrda3.xrda3_launcher.R;

public class PermissionActivity extends AppCompatActivity {

    Switch swStorage, swBluetooth;
    Button btnAllow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);
        Intent intent = new Intent(PermissionActivity.this, MusicHomeActivity.class);
        startActivity(intent);

        swStorage = findViewById(R.id.swStorage);
        swBluetooth = findViewById(R.id.swBluetooth);
        btnAllow = findViewById(R.id.btnAllowAll);

        btnAllow.setOnClickListener(v -> checkPermissions());
    }

    void checkPermissions() {

        if (swStorage.isChecked()) {
            askStoragePermission();
            return;
        }

        if (swBluetooth.isChecked()) {
            askBluetoothPermission();
            return;
        }

        openPlayer();
    }

    void askStoragePermission() {
        if (Build.VERSION.SDK_INT >= 33) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_MEDIA_AUDIO}, 100);
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
        }
    }

    void askBluetoothPermission() {
        if (Build.VERSION.SDK_INT >= 31) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.BLUETOOTH_CONNECT}, 200);
        } else {
            openPlayer();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length > 0 &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
        }

        openPlayer();
    }

    void openPlayer() {
        startActivity(new Intent(this, PlayerActivity.class));
        finish();
    }
}
