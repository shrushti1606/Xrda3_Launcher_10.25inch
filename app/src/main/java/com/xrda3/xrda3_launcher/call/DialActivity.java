package com.xrda3.xrda3_launcher.call;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;

import com.xrda3.xrda3_launcher.R;
import com.xrda3.xrda3_launcher.call.adapter.HomeCallAdapter;
import com.xrda3.xrda3_launcher.call.model.HomeCallModel;

import java.util.ArrayList;

public class DialActivity extends AppCompatActivity {

    private ImageView click_star, click_recent, click_keypad, click_con, click_set;
    private ListView listContacts;
    private HomeCallAdapter adapter;
    private ArrayList<HomeCallModel> callList;

    private EditText conNumber;
    private ImageButton btnDelete;
    private AppCompatImageButton btnCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dial);

        // ================= INIT VIEWS =================
        conNumber = findViewById(R.id.conNumber);
        btnDelete = findViewById(R.id.btnDelete);
        btnCall = findViewById(R.id.btnCall);

        listContacts = findViewById(R.id.listContacts);

        click_star = findViewById(R.id.click_star);
        click_recent = findViewById(R.id.click_recent);
        click_keypad = findViewById(R.id.click_keypad);
        click_con = findViewById(R.id.click_con);
        click_set = findViewById(R.id.click_set);

        // ================= NAVIGATION =================
        click_star.setOnClickListener(v ->
                startActivity(new Intent(this, FavouriteCallActivity.class)));

        click_recent.setOnClickListener(v ->
                startActivity(new Intent(this, RecentCallActivity.class)));

        click_keypad.setOnClickListener(v ->
                startActivity(new Intent(this, DialActivity.class)));

        click_con.setOnClickListener(v ->
                startActivity(new Intent(this, ContactListActivity.class)));

        click_set.setOnClickListener(v ->
                startActivity(new Intent(this, SettingsCallActivity.class)));

        // ================= KEYPAD =================
        int[] keypadIds = {
                R.id.btn1, R.id.btn2, R.id.btn3,
                R.id.btn4, R.id.btn5, R.id.btn6,
                R.id.btn7, R.id.btn8, R.id.btn9,
                R.id.btnStar, R.id.btn0, R.id.btnHash
        };

        View.OnClickListener keypadClickListener = v -> {
            TextView key = (TextView) v;
            conNumber.append(key.getText().toString());
        };

        for (int id : keypadIds) {
            findViewById(id).setOnClickListener(keypadClickListener);
        }

        btnDelete.setOnClickListener(v -> {
            String number = conNumber.getText().toString();
            if (!number.isEmpty()) {
                conNumber.setText(number.substring(0, number.length() - 1));
                conNumber.setSelection(conNumber.getText().length());
            }
        });

        btnCall.setOnClickListener(v -> {
            String number = conNumber.getText().toString().trim();

            if (number.isEmpty()) {
                Toast.makeText(this, "Enter number", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent(this, DialedActivity.class);
            intent.putExtra("dialed_number", number);
            startActivity(intent);
        });

        // ================= LIST SETUP (IMPORTANT) =================
        setupCallList();

        listContacts.setOnItemClickListener((parent, view, position, id) -> {
            HomeCallModel call = callList.get(position);
            String phone = call.getPhone();

            if (!phone.matches("\\d{10}")) {
                Toast.makeText(this, "Number must be 10 digits!", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent(this, DialedActivity.class);
            intent.putExtra("name", call.getName());
            intent.putExtra("phone", phone);
            startActivity(intent);
        });
    }

    // ================= CALL LIST DATA =================
    private void setupCallList() {
        callList = new ArrayList<>();

        callList.add(new HomeCallModel(
                "User 1", "Missed call · 10:42 AM · Mobile", "9876543210", 1));

        callList.add(new HomeCallModel(
                "User 2", "Incoming call · 09:15 AM · Mobile", "7876543218", 0));

        callList.add(new HomeCallModel(
                "User 3", "Outgoing call · Yesterday · Work", "8876543210", 2));

        callList.add(new HomeCallModel(
                "User 4", "Missed call · 08:30 AM · Mobile", "9875543246", 1));

        callList.add(new HomeCallModel(
                "User 5", "Outgoing call · 07:20 AM · Mobile", "7896543210", 2));

        adapter = new HomeCallAdapter(this, callList);
        listContacts.setAdapter(adapter);
    }
}
