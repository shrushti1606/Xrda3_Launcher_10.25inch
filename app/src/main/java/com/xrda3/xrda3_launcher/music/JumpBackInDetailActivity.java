package com.xrda3.xrda3_launcher.music;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.xrda3.xrda3_launcher.R;

public class JumpBackInDetailActivity extends AppCompatActivity {

    private ImageView detailImage;
    private TextView detailTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jump_back_in_detail);

        detailImage = findViewById(R.id.detailImage);
        detailTitle = findViewById(R.id.detailTitle);

        Intent intent = getIntent();
        if (intent != null) {
            detailTitle.setText(intent.getStringExtra("TITLE"));
            detailImage.setImageResource(intent.getIntExtra("IMAGE", 0));
        }
    }
}
