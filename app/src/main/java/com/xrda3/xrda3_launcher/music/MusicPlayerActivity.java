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

    ImageButton btnplay, btnPrevious, btnNext;
    ImageView click_home, click_menu, click_down, click_set, click_heart;
    ImageView songImage;

    SeekBar seekbar;
    TextView musicstart, musicend, musicTitle, musicArtist;

    MediaPlayer mediaPlayer;
    boolean isPlaying = false;
    Handler handler = new Handler();

    int currentIndex = 0;

    // 🎵 SONG LIST (RAW)
    int[] songList = {
            R.raw.happymusic1,
            R.raw.happymusic2,
            R.raw.midnight_city,
            R.raw.morningcity1,
            R.raw.travelling,
            R.raw.travelling2,
            R.raw.travelling3,
            R.raw.travelling4
    };

    // 🖼 SONG IMAGES (DRAWABLE)
    int[] songImages = {
            R.drawable.chillvibes,
            R.drawable.artist,
            R.drawable.midnightdrive,
            R.drawable.midnightdrive,
            R.drawable.morningvibes,
            R.drawable.tophits,
            R.drawable.chillvibes,
            R.drawable.chillvibes
    };

    String[] songTitles = {
            "Happy Music",
            "Blinding Lights",
            "Midnight City",
            "Morning City",
            "Travelling",
            "Travelling 2",
            "Travelling 3",
            "Travelling 4"
    };

    String[] songArtists = {
            "Unknown",
            "The Weeknd",
            "M83",
            "Daft Punk",
            "Unknown",
            "Unknown",
            "Unknown",
            "Unknown"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);

        initViews();

        Intent intent = getIntent();
        int songResId = intent.getIntExtra("songResId", songList[0]);
        String title = intent.getStringExtra("title");
        String artist = intent.getStringExtra("artist");

        // 🔍 Find clicked song index
        for (int i = 0; i < songList.length; i++) {
            if (songList[i] == songResId) {
                currentIndex = i;
                break;
            }
        }

        playSong(currentIndex, title, artist);

        btnplay.setOnClickListener(v -> togglePlayPause());

        btnNext.setOnClickListener(v -> {
            currentIndex = (currentIndex + 1) % songList.length;
            playSong(currentIndex, null, null);
        });

        btnPrevious.setOnClickListener(v -> {
            currentIndex = (currentIndex - 1 + songList.length) % songList.length;
            playSong(currentIndex, null, null);
        });

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
        setupNavigation();
    }

    private void initViews() {
        btnplay = findViewById(R.id.btnplay);
        btnPrevious = findViewById(R.id.btnprev);
        btnNext = findViewById(R.id.btnnext);

        songImage = findViewById(R.id.songImage);

        seekbar = findViewById(R.id.seekbar);
        musicstart = findViewById(R.id.musicstart);
        musicend = findViewById(R.id.musicend);
        musicTitle = findViewById(R.id.musictitle);
        musicArtist = findViewById(R.id.musicartist);

        click_home = findViewById(R.id.click_home);
        click_menu = findViewById(R.id.click_menu);
        click_down = findViewById(R.id.click_down);
        click_set = findViewById(R.id.click_set);
        click_heart = findViewById(R.id.click_heart);
    }

    private void playSong(int index, String title, String artist) {

        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }

        mediaPlayer = MediaPlayer.create(this, songList[index]);
        mediaPlayer.start();

        isPlaying = true;
        btnplay.setImageResource(R.drawable.ic_pause);

        musicTitle.setText(title != null ? title : songTitles[index]);
        musicArtist.setText(artist != null ? artist : songArtists[index]);

        // 🖼 CHANGE IMAGE WITH SONG
        songImage.setImageResource(songImages[index]);
        songImage.setAlpha(0f);
        songImage.animate().alpha(1f).setDuration(400).start();

        seekbar.setMax(mediaPlayer.getDuration());
        musicend.setText(formatTime(mediaPlayer.getDuration()));

        mediaPlayer.setOnCompletionListener(mp -> btnNext.performClick());
    }

    private void togglePlayPause() {
        if (mediaPlayer == null) return;

        if (isPlaying) {
            mediaPlayer.pause();
            btnplay.setImageResource(R.drawable.ic_play);
        } else {
            mediaPlayer.start();
            btnplay.setImageResource(R.drawable.ic_pause);
        }
        isPlaying = !isPlaying;
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

    private void setupNavigation() {
        click_home.setOnClickListener(v ->
                startActivity(new Intent(this, MusicHomeActivity.class)));

        click_menu.setOnClickListener(v ->
                startActivity(new Intent(this, MusicHomeActivity.class)));

        click_down.setOnClickListener(v ->
                startActivity(new Intent(this, MusicDownloadActivity.class)));

        click_set.setOnClickListener(v ->
                startActivity(new Intent(this, MusicSettingsActivity.class)));

        click_heart.setOnClickListener(v ->
                startActivity(new Intent(this, MusicFavActivity.class)));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
