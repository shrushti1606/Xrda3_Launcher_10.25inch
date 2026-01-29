package com.xrda3.xrda3_launcher.call;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.xrda3.xrda3_launcher.R;
import com.xrda3.xrda3_launcher.call.adapter.HomeCallAdapter;
import com.xrda3.xrda3_launcher.call.model.CallLogModel;
import com.xrda3.xrda3_launcher.call.model.ContactListModel;
import com.xrda3.xrda3_launcher.call.model.HomeCallModel;

import java.util.ArrayList;

public class ContactListActivity extends AppCompatActivity {

    private ListView listContacts;
    private HomeCallAdapter adapter;
    private ArrayList<HomeCallModel> callList;

    private ImageView clickStar, clickRecent, clickKeypad, clickContacts, clickSettings;
    private View rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        rootView = findViewById(android.R.id.content);

        setupToolbar();
        initViews();
        setupNavigation();
        setupCallList();
        setupFab();
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void initViews() {
        listContacts = findViewById(R.id.listContacts);

        clickStar = findViewById(R.id.click_star);
        clickRecent = findViewById(R.id.click_recent);
        clickKeypad = findViewById(R.id.click_keypad);
        clickContacts = findViewById(R.id.click_con);
        clickSettings = findViewById(R.id.click_set);
    }

    private void setupNavigation() {

        clickStar.setOnClickListener(v ->
                startActivity(new Intent(this, FavouriteCallActivity.class)));

        clickRecent.setOnClickListener(v ->
                startActivity(new Intent(this, RecentCallActivity.class)));

        clickKeypad.setOnClickListener(v ->
                startActivity(new Intent(this, DialActivity.class)));

        clickContacts.setOnClickListener(v -> {});

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

    }

    private void setupCallList() {

        callList = new ArrayList<>();
        callList.add(new HomeCallModel("User 1",
                "Missed call · 10:42 AM · Mobile", "9876543210", 1));
        callList.add(new HomeCallModel("User 2",
                "Incoming call · 09:15 AM · Mobile",  "7876543218",0));
        callList.add(new HomeCallModel("User 3",
                "Outgoing call · Yesterday · Work",  "8876543210",2));
        callList.add(new HomeCallModel("User 4",
                "Missed call · 08:30 AM · Mobile",  "9875543246",1));
        callList.add(new HomeCallModel("User 5",
                "Outgoing call · 07:20 AM · Mobile",  "7896543210",2));

        adapter = new HomeCallAdapter(this, callList);
        listContacts.setAdapter(adapter);
    }


    private void setupFab() {
        FloatingActionButton fabAdd = findViewById(R.id.add);
        fabAdd.setOnClickListener(v ->
                Snackbar.make(rootView, "Add contact", Snackbar.LENGTH_LONG).show()
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
