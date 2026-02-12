package com.xrda3.xrda3_launcher.video;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.xrda3.xrda3_launcher.R;

public class FolderActivity extends AppCompatActivity {

    private LinearLayout folderContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folder);

        folderContainer = findViewById(R.id.folderContainer);

        String folderName = getIntent().getStringExtra("folderName");
        setTitle(folderName != null ? folderName : "Folder");

        loadFolders();
    }

    private void loadFolders() {

        LinearLayout horizontal = new LinearLayout(this);
        horizontal.setOrientation(LinearLayout.HORIZONTAL);
        folderContainer.addView(horizontal);

        String[] videos = {"Video 1", "Video 2", "Video 3", "Video 4"};

        for (String name : videos) {

            LinearLayout card = new LinearLayout(this);
            card.setOrientation(LinearLayout.VERTICAL);
            card.setPadding(16, 16, 16, 16);
            card.setBackgroundColor(0xFF1A1A1A);

            LinearLayout.LayoutParams params =
                    new LinearLayout.LayoutParams(300, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.rightMargin = 20;
            card.setLayoutParams(params);

            ImageView cover = new ImageView(this);
            cover.setImageResource(R.drawable.cover_image1);
            cover.setLayoutParams(new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, 180));
            cover.setScaleType(ImageView.ScaleType.CENTER_CROP);

            TextView text = new TextView(this);
            text.setText(name);
            text.setTextColor(0xFFFFFFFF);
            text.setPadding(8, 8, 8, 8);

            card.addView(cover);
            card.addView(text);

            horizontal.addView(card);
        }
    }
}
