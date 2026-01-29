package com.xrda3.xrda3_launcher.call;

import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.xrda3.xrda3_launcher.R;
import com.xrda3.xrda3_launcher.call.adapter.RecentCallAdapter;
import com.xrda3.xrda3_launcher.call.model.RecentCallModel;

import java.util.ArrayList;

public class RecentCallActivity extends AppCompatActivity {

    private ImageView click_star, click_keypad, click_con, click_set;
    private ListView listContacts;
    private ArrayList<RecentCallModel> callList;
    private RecentCallAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent_call);



        click_star = findViewById(R.id.click_star);
        click_star.setOnClickListener(v ->
                startActivity(new Intent(this, FavouriteCallActivity.class)));


        click_keypad = findViewById(R.id.click_keypad);
        click_keypad.setOnClickListener(v ->
                startActivity(new Intent(this, DialActivity.class)));

        click_con = findViewById(R.id.click_con);
        click_con.setOnClickListener(v ->
                startActivity(new Intent(this, ContactListActivity.class)));


        click_set = findViewById(R.id.click_set);
        click_set.setOnClickListener(v ->
                startActivity(new Intent(this, SettingsCallActivity.class)));


        listContacts = findViewById(R.id.listContacts);
        callList = new ArrayList<>();

        callList.add(new RecentCallModel("Rahul Sharma",
                "Missed call · 10:42 AM · Mobile", "9876543210"));
        callList.add(new RecentCallModel("Mom",
                "Incoming call · 09:15 AM · Mobile", "9123456789"));
        callList.add(new RecentCallModel("Office",
                "Outgoing call · Yesterday · Work", "9988776655"));


        adapter = new RecentCallAdapter(this, callList);
        listContacts.setAdapter(adapter);

        listContacts.setOnItemClickListener((parent, view, position, id) -> {
            RecentCallModel call = callList.get(position);
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
    }
}
