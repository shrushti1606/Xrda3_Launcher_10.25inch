package com.xrda3.xrda3_launcher.music;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.xrda3.xrda3_launcher.R;

public class MusicPlayerActivity extends AppCompatActivity {

    ImageButton btnplay;
    ImageView click_home, click_menu, click_library, click_set, click_heart;
    SeekBar seekbar;
    TextView musicstart, musicend, musicTitle, musicArtist;

    MediaPlayer mediaPlayer;
    boolean isPlaying = false;
    Handler handler = new Handler();

    int songResId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);

        btnplay = findViewById(R.id.btnplay);
        seekbar = findViewById(R.id.seekbar);
        musicstart = findViewById(R.id.musicstart);
        musicend = findViewById(R.id.musicend);
        musicTitle = findViewById(R.id.musictitle);
        musicArtist = findViewById(R.id.musicartist);

        click_home = findViewById(R.id.click_home);
        click_menu = findViewById(R.id.click_menu);
        click_library = findViewById(R.id.click_library);
        click_set = findViewById(R.id.click_set);
        click_heart = findViewById(R.id.click_heart);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String artist = intent.getStringExtra("artist");
        songResId = intent.getIntExtra("songResId", -1);

        if (title != null) musicTitle.setText(title);
        if (artist != null) musicArtist.setText(artist);

        if (songResId != -1) {
            mediaPlayer = MediaPlayer.create(this, songResId);
        } else {
            mediaPlayer = MediaPlayer.create(this, R.raw.midnight_city);
        }

        seekbar.setMax(mediaPlayer.getDuration());
        musicend.setText(formatTime(mediaPlayer.getDuration()));

        mediaPlayer.start();
        isPlaying = true;
        btnplay.setImageResource(R.drawable.ic_pause);

        btnplay.setOnClickListener(v -> togglePlayPause());

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser && mediaPlayer != null) {
                    mediaPlayer.seekTo(progress);
                }
            }

            @Override public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        updateSeekBar();

        click_home.setOnClickListener(v ->
                startActivity(new Intent(this, MusicHomeActivity.class)));

        click_menu.setOnClickListener(v ->
                startActivity(new Intent(this, MusicHomeActivity.class)));

        click_library.setOnClickListener(v ->
                startActivity(new Intent(this, MusicLibraryActivity.class)));

        click_set.setOnClickListener(v ->
                startActivity(new Intent(this, MusicSettingsActivity.class)));

        click_heart.setOnClickListener(v ->
                startActivity(new Intent(this, MusicFavActivity.class)));
    }

    private void togglePlayPause() {
        if (mediaPlayer == null) return;

        if (isPlaying) {
            mediaPlayer.pause();
            btnplay.setImageResource(R.drawable.ic_play);
            isPlaying = false;
        } else {
            mediaPlayer.start();
            btnplay.setImageResource(R.drawable.ic_pause);
            isPlaying = true;
        }
    }

    private void updateSeekBar() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mediaPlayer != null && isPlaying) {
                    int pos = mediaPlayer.getCurrentPosition();
                    seekbar.setProgress(pos);
                    musicstart.setText(formatTime(pos));
                }
                handler.postDelayed(this, 1000);
            }
        }, 0);
    }

    private String formatTime(int millis) {
        int min = (millis / 1000) / 60;
        int sec = (millis / 1000) % 60;
        return String.format("%d:%02d", min, sec);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
