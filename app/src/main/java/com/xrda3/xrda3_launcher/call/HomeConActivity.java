package com.xrda3.xrda3_launcher.call;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.xrda3.xrda3_launcher.R;
import com.xrda3.xrda3_launcher.call.adapter.HomeCallAdapter;
import com.xrda3.xrda3_launcher.call.model.HomeCallModel;
import com.xrda3.xrda3_launcher.call.model.RecentCallModel;

import java.util.ArrayList;

public class HomeConActivity extends AppCompatActivity {

    private ListView listView, listContacts;
    private ImageView clickStar, clickRecent, clickKeypad, clickContacts, clickSettings;
    private HomeCallAdapter adapter;
    private ArrayList<HomeCallModel> callList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_con);

        clickStar = findViewById(R.id.click_star);
        clickStar.setOnClickListener(v ->
                startActivity(new Intent(this, FavouriteCallActivity.class)));

        clickRecent = findViewById(R.id.click_recent);
        clickRecent.setOnClickListener(v ->
                startActivity(new Intent(this, RecentCallActivity.class)));

        clickKeypad = findViewById(R.id.click_keypad);
        clickKeypad.setOnClickListener(v ->
                startActivity(new Intent(this, DialActivity.class)));

        clickContacts = findViewById(R.id.click_con);
        clickContacts.setOnClickListener(v -> {});

        clickSettings = findViewById(R.id.click_set);
        clickSettings.setOnClickListener(v ->
                startActivity(new Intent(this, SettingsCallActivity.class)));



        listContacts= findViewById(R.id.listContacts);
        listContacts.setOnItemClickListener((parent, view, position, id) -> {
            HomeCallModel call = callList.get(position);
            String phone = call.getPhone();
            if (phone.length() != 10 || !phone.matches("\\d{10}")) {
                Toast.makeText(this, "Number must be 10 digits!", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent(this, DialedActivity.class);
            intent.putExtra("name", call.getName());
            intent.putExtra("phone", phone);
            startActivity(intent);
        });


            callList = new ArrayList<>();
        callList.add(new HomeCallModel("Rahul Sharma",
                "Missed call · 10:42 AM · Mobile", "9876543210", 1));
        callList.add(new HomeCallModel("Amit Verma",
                "Incoming call · 09:15 AM · Mobile", "9123456789", 0));
        callList.add(new HomeCallModel("Neha Singh",
                "Outgoing call · Yesterday · Work", "9988776655", 2));
        callList.add(new HomeCallModel("Aman Singh",
                "Outgoing call · Yesterday · Work", "9488776655", 2));
        callList.add(new HomeCallModel("Rohit Sharma",
                "Missed call · 10:42 AM · Mobile", "9876543210", 1));
        callList.add(new HomeCallModel("Anuja Verma",
                "Incoming call · 09:15 AM · Mobile", "9123456789", 0));


        adapter = new HomeCallAdapter(this, callList);
        listContacts.setAdapter(adapter);
    }
}
