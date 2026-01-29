package com.xrda3.xrda3_launcher.call;

import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.telecom.Call;
import android.view.View;
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

    private Call call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialed);

        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        handler = new Handler(Looper.getMainLooper());

        initViews();

        call = CallManager.currentCall;

        if (call == null) {
            Toast.makeText(this, "No active call", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        call.registerCallback(callCallback);

        setListeners();

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

        // 🔇 MUTE
        btnMute.setOnClickListener(v -> {
            isMuted = !isMuted;
            audioManager.setMicrophoneMute(isMuted);
            Toast.makeText(this, isMuted ? "Muted" : "Unmuted", Toast.LENGTH_SHORT).show();
        });

        // 🔊 SPEAKER
        btnSpeaker.setOnClickListener(v -> {
            isSpeakerOn = !isSpeakerOn;
            audioManager.setSpeakerphoneOn(isSpeakerOn);
            Toast.makeText(this, isSpeakerOn ? "Speaker ON" : "Speaker OFF", Toast.LENGTH_SHORT).show();
        });

        // ⏸ HOLD / RESUME
        btnHold.setOnClickListener(v -> {
            if (call == null) return;

            if (call.getState() == Call.STATE_ACTIVE) {
                call.hold();
                Toast.makeText(this, "Call On Hold", Toast.LENGTH_SHORT).show();
            } else if (call.getState() == Call.STATE_HOLDING) {
                call.unhold();
                Toast.makeText(this, "Call Resumed", Toast.LENGTH_SHORT).show();
            }
        });

        // 📞 ADD CALL (SYSTEM DIALER)
        btnAddCall.setOnClickListener(v ->
                Toast.makeText(this, "Add Call (System handled)", Toast.LENGTH_SHORT).show()
        );

        // 🔢 KEYPAD
        btnKeypad.setOnClickListener(v ->
                Toast.makeText(this, "Keypad Clicked", Toast.LENGTH_SHORT).show()
        );

        // 🔵 BLUETOOTH
        btnBluetooth.setOnClickListener(v -> {
            audioManager.startBluetoothSco();
            audioManager.setBluetoothScoOn(true);
            Toast.makeText(this, "Bluetooth Enabled", Toast.LENGTH_SHORT).show();
        });

        // ❌ END CALL (REAL)
        btnEndCall.setOnClickListener(v -> {
            if (call != null) {
                call.disconnect();   // 🔥 REAL SIM CALL END
            }
            finish();
        });
    }

    // ⏱ CALL TIMER
    private void startTimer() {
        handler.postDelayed(timerRunnable, 1000);
    }

    private void stopTimer() {
        handler.removeCallbacks(timerRunnable);
    }

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

    // 📞 CALL STATE LISTENER
    private final Call.Callback callCallback = new Call.Callback() {
        @Override
        public void onStateChanged(Call call, int state) {
            runOnUiThread(() -> {
                switch (state) {

                    case Call.STATE_ACTIVE:
                        startTimer();
                        break;

                    case Call.STATE_DISCONNECTED:
                        stopTimer();
                        finish();
                        break;
                }
            });
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopTimer();
        if (call != null) {
            call.unregisterCallback(callCallback);
        }
    }

    public void updateDialedInfo(String name, String phone) {
        if (name != null) conName.setText(name);
        if (phone != null) conNum.setText(phone);
        seconds = 0;
        callTimer.setText("00:00");
    }
}
