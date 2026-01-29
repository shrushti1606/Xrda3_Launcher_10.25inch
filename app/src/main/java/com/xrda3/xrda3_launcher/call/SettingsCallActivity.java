package com.xrda3.xrda3_launcher.call;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.xrda3.xrda3_launcher.R;

public class SettingsCallActivity extends AppCompatActivity {

    // Main views
    private LinearLayout topStatusBar;
    private LinearLayout callNavBar;
    private LinearLayout footer;
    private TextView setTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_call); // <-- your XML file name

        initViews();
        setupUI();
        setupClicks();
    }

    private void initViews() {
        topStatusBar = findViewById(R.id.topStatusBar);
        callNavBar = findViewById(R.id.callNavBar);
        footer = findViewById(R.id.footer);
        setTitle = findViewById(R.id.setTitle);
    }

    private void setupUI() {
        setTitle.setText("Settings");
    }

    private void setupClicks() {

        // Example: click on navigation bar
        callNavBar.setOnClickListener(v -> {
            // handle navigation click
        });

        // Footer click
        footer.setOnClickListener(v -> {
            // handle footer action
        });
    }
}
