package com.xrda3.xrda3_launcher.call;

import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.xrda3.xrda3_launcher.R;

public class DialedActivity extends AppCompatActivity {

    private TextView conName, conNum, callTimer;
    private LinearLayout btnMute, btnKeypad, btnSpeaker, btnHold, btnAddCall, btnBluetooth;
    private TextView btnEndCall;

    private AudioManager audioManager;
    private boolean isMuted = false;
    private boolean isSpeakerOn = false;

    private Handler handler;
    private int seconds = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialed);

        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        handler = new Handler(Looper.getMainLooper());

        initViews();
        setListeners();
        startTimer();
        String name = getIntent().getStringExtra("name");
        String phone = getIntent().getStringExtra("phone");
        updateDialedInfo(name, phone);
    }

    private void initViews() {

        conName = findViewById(R.id.conName);
        conNum = findViewById(R.id.conNum);
        callTimer = findViewById(R.id.callTimer);

        btnMute = findViewById(R.id.btnMute);
        btnKeypad = findViewById(R.id.btnKeypad);
        btnSpeaker = findViewById(R.id.btnSpeaker);
        btnHold = findViewById(R.id.btnHold);
        btnAddCall = findViewById(R.id.btnAddCall);
        btnBluetooth = findViewById(R.id.btnBluetooth);
        btnEndCall = findViewById(R.id.btnEndCall);
    }

    private void setListeners() {
        btnMute.setOnClickListener(v -> {
            isMuted = !isMuted;
            audioManager.setMicrophoneMute(isMuted);
            Toast.makeText(this, isMuted ? "Muted" : "Unmuted", Toast.LENGTH_SHORT).show();
        });

        btnSpeaker.setOnClickListener(v -> {
            isSpeakerOn = !isSpeakerOn;
            audioManager.setSpeakerphoneOn(isSpeakerOn);
            Toast.makeText(this, isSpeakerOn ? "Speaker ON" : "Speaker OFF", Toast.LENGTH_SHORT).show();
        });

        btnKeypad.setOnClickListener(v -> Toast.makeText(this,
                "Keypad clicked", Toast.LENGTH_SHORT).show());
        btnHold.setOnClickListener(v -> Toast.makeText(this,
                "Call on hold", Toast.LENGTH_SHORT).show());
        btnAddCall.setOnClickListener(v -> Toast.makeText(this,
                "Add call clicked", Toast.LENGTH_SHORT).show());
        btnBluetooth.setOnClickListener(v -> Toast.makeText(this,
                "Bluetooth clicked", Toast.LENGTH_SHORT).show());
        btnEndCall.setOnClickListener(v -> {
            Toast.makeText(this, "Call ended", Toast.LENGTH_SHORT).show();
            finish();
        });
    }

    private void startTimer() { handler.postDelayed(timerRunnable, 1000); }
    private void stopTimer() { handler.removeCallbacks(timerRunnable); }

    private final Runnable timerRunnable = new Runnable() {
        @Override
        public void run() {
            seconds++;
            int min = seconds / 60;
            int sec = seconds % 60;
            callTimer.setText(String.format("%02d:%02d", min, sec));
            handler.postDelayed(this, 1000);
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopTimer();
    }
    public void updateDialedInfo(String name, String phone) {
        if (name != null && phone != null) {
            if (phone.length() == 10 && phone.matches("\\d{10}")) {
                conName.setText(name);
                conNum.setText(phone);

                seconds = 0;
                callTimer.setText("00:00");
            } else {
                Toast.makeText(this, "Number must be 10 digits!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}