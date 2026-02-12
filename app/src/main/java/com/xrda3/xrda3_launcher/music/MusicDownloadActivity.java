package com.xrda3.xrda3_launcher.music;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xrda3.xrda3_launcher.R;
import com.xrda3.xrda3_launcher.music.adapter.MusicDownloadsAdapter;
import com.xrda3.xrda3_launcher.music.model.MusicDownloadModel;

import java.util.ArrayList;

public class MusicDownloadActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_download);

        recyclerView = findViewById(R.id.recyclerDownloads);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<MusicDownloadModel> list = new ArrayList<>();

        list.add(new MusicDownloadModel(
                "Happy Music", "M83", "2:07",
                R.drawable.ic_music, R.raw.happymusic1
        ));

        list.add(new MusicDownloadModel(
                "Blinding Lights", "The Weeknd", "2:19",
                R.drawable.ic_music, R.raw.happymusic2
        ));

        list.add(new MusicDownloadModel(
                "Midnight City", "M83", "4:03",
                R.drawable.ic_music, R.raw.midnight_city
        ));

        list.add(new MusicDownloadModel(
                "Morning City", "Daft Punk", "1:44",
                R.drawable.ic_music, R.raw.morningcity1
        ));

        recyclerView.setAdapter(new MusicDownloadsAdapter(this, list));

    }
}
