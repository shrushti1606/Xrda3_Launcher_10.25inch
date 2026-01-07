package com.xrda3.xrda3_launcher.video.videohomeactivity;

import android.Manifest;
import android.content.ContentUris;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xrda3.xrda3_launcher.R;
import com.xrda3.xrda3_launcher.video.adapter.FolderAdapter;
import com.xrda3.xrda3_launcher.video.model.VideoModel;

import java.util.ArrayList;

public class VideoHomeActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FolderAdapter adapter;

    ArrayList<VideoModel> videoList = new ArrayList<>();
    ArrayList<String> folderList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.videohomeactivity);

        recyclerView = findViewById(R.id.folder_recyclerview);

        // 1️⃣ Load RAW videos (always)
        videoList.addAll(loadRawVideos());

        // 2️⃣ Load MediaStore videos (if allowed)
        if (hasPermission()) {
            videoList.addAll(loadMediaStoreVideos());
        }

        if (videoList.isEmpty()) {
            Toast.makeText(this, "No videos found", Toast.LENGTH_SHORT).show();
            return;
        }

        // 3️⃣ Extract folders
        for (VideoModel model : videoList) {
            if (!folderList.contains(model.getFolder())) {
                folderList.add(model.getFolder());
            }
        }

        adapter = new FolderAdapter(folderList, videoList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    // 🔹 RAW VIDEOS
    private ArrayList<VideoModel> loadRawVideos() {
        ArrayList<VideoModel> list = new ArrayList<>();

        int[] rawVideos = {
                R.raw.demo_video   // demo_video.mp4
        };

        for (int id : rawVideos) {
            Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + id);
            list.add(new VideoModel(
                    String.valueOf(id),
                    uri.toString(),
                    "Demo Video",
                    "00:30",
                    "XRDA3_Launcher"
            ));
        }
        return list;
    }

    // 🔹 MEDIASTORE VIDEOS
    private ArrayList<VideoModel> loadMediaStoreVideos() {
        ArrayList<VideoModel> list = new ArrayList<>();

        Uri uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        Cursor cursor = getContentResolver().query(
                uri,
                null,
                null,
                null,
                MediaStore.Video.Media.DATE_ADDED + " DESC"
        );

        if (cursor != null) {
            while (cursor.moveToNext()) {
                String id = cursor.getString(
                        cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID));
                String title = cursor.getString(
                        cursor.getColumnIndexOrThrow(MediaStore.Video.Media.TITLE));
                String duration = cursor.getString(
                        cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DURATION));
                String folder = cursor.getString(
                        cursor.getColumnIndexOrThrow(MediaStore.Video.Media.BUCKET_DISPLAY_NAME));

                Uri videoUri = ContentUris.withAppendedId(uri, Long.parseLong(id));

                list.add(new VideoModel(id, videoUri.toString(), title, duration, folder));
            }
            cursor.close();
        }
        return list;
    }

    // 🔹 PERMISSION CHECK
    private boolean hasPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            return checkSelfPermission(Manifest.permission.READ_MEDIA_VIDEO)
                    == PackageManager.PERMISSION_GRANTED;
        } else {
            return checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED;
        }
    }
}
