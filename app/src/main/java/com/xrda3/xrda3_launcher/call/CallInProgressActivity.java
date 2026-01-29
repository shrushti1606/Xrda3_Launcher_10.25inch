package com.xrda3.xrda3_launcher.call;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.xrda3.xrda3_launcher.R;

public class CallInProgressActivity extends AppCompatActivity {

    private TextView callTimer;
    private int seconds = 0;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialed);

        callTimer = findViewById(R.id.callTimer);
        Button btnEndCall = findViewById(R.id.btnEndCall);

        startTimer();

        btnEndCall.setOnClickListener(v -> {
            Toast.makeText(this, "Call Ended", Toast.LENGTH_SHORT).show();
            finish();
        });
    }

    private void startTimer() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                seconds++;
                int min = seconds / 60;
                int sec = seconds % 60;
                callTimer.setText(String.format("%02d:%02d", min, sec));
                handler.postDelayed(this, 1000);
            }
        }, 1000);
    }
}
