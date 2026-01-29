package com.xrda3.xrda3_launcher.call;
import android.content.Intent;
import android.telecom.Call;
import android.telecom.InCallService;

public class  DialedCallService extends InCallService {

    @Override
    public void onCallAdded(Call call) {
        super.onCallAdded(call);

        // 🔥 SAVE REAL CALL
        CallManager.currentCall = call;

        // 🔥 OPEN YOUR CUSTOM CALL UI
        Intent intent = new Intent(this, DialedActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void onCallRemoved(Call call) {
        super.onCallRemoved(call);
        CallManager.currentCall = null;
    }
}

