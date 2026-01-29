package com.xrda3.xrda3_launcher.video.ui;

import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.xrda3.xrda3_launcher.R;

public class VideoPlayerActivity extends AppCompatActivity {

    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videoplayer);

        videoView = findViewById(R.id.videoView);

        Uri uri = Uri.parse(getIntent().getStringExtra("videoUri"));
        videoView.setVideoURI(uri);
        videoView.start();

        Button playPause = findViewById(R.id.playPause);
        Button forward = findViewById(R.id.forward);
        Button backward = findViewById(R.id.backward);

        playPause.setOnClickListener(v -> {
            if (videoView.isPlaying()) videoView.pause();
            else videoView.start();
        });

        forward.setOnClickListener(v ->
                videoView.seekTo(videoView.getCurrentPosition() + 10000));

        backward.setOnClickListener(v ->
                videoView.seekTo(videoView.getCurrentPosition() - 10000));
    }
}
