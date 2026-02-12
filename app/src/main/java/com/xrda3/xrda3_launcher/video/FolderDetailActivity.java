package com.xrda3.xrda3_launcher.video;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.xrda3.xrda3_launcher.R;

public class FolderDetailActivity extends AppCompatActivity {

    private TextView folderTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folder_detail);

        folderTitle = findViewById(R.id.folderTitle);

        // Get folder name from Intent
        String folderName = getIntent().getStringExtra("folderName");
        if (folderName != null) {
            folderTitle.setText(folderName);
        } else {
            folderTitle.setText("Folder"); // default title
        }


    }
}
