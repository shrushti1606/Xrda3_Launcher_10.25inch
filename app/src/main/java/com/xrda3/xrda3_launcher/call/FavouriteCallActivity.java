package com.xrda3.xrda3_launcher.call;

import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.xrda3.xrda3_launcher.R;
import com.xrda3.xrda3_launcher.call.adapter.FavouriteCallAdapter;
import com.xrda3.xrda3_launcher.call.adapter.FavouriteCallAdapter;
import com.xrda3.xrda3_launcher.call.model.FavouriteCallModel;
import com.xrda3.xrda3_launcher.call.model.FavouriteCallModel;

import java.util.ArrayList;

public class FavouriteCallActivity extends AppCompatActivity {

    private LinearLayout idfav;
    private TextView idpinned;
    private Button btnAddFav;

    private ArrayList<FavouriteCallModel> favList = new ArrayList<>();
    private FavouriteCallAdapter adapter;

    private int pinnedCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_call);

        idfav = findViewById(R.id.idfav);
        idpinned = findViewById(R.id.idpinned);
        btnAddFav = findViewById(R.id.btnAddFav);

        adapter = new FavouriteCallAdapter(this, idfav, favList);
        updatePinnedText();

        btnAddFav.setOnClickListener(v -> {

            if (favList.size() >= 10) {
                Toast.makeText(this,
                        "Max 10 favorites allowed",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            pinnedCount++;

            FavouriteCallModel model =
                    new FavouriteCallModel(
                            "User " + pinnedCount,
                            "Mobile",
                            "987654321" + pinnedCount
                    );

            favList.add(model);
            adapter.refresh();
            updatePinnedText();
        });
    }

    private void updatePinnedText() {
        idpinned.setText(favList.size() + " contacts pinned");
    }
}
