package com.xrda3.xrda3_launcher.music;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xrda3.xrda3_launcher.R;
import com.xrda3.xrda3_launcher.music.adapter.UpNextAdapter;
import com.xrda3.xrda3_launcher.music.model.SongModel;

import java.util.ArrayList;

public class MusicPlayerActivity extends AppCompatActivity {

    ImageButton btnplay, btnPrevious, btnNext;
    ImageView songImage;
    SeekBar seekbar;
    TextView musicstart, musicend, musicTitle, musicArtist;

    RecyclerView upNextRecycler;
    UpNextAdapter upNextAdapter;

    ArrayList<SongModel> songList;
    ArrayList<SongModel> upNextList = new ArrayList<>();

    MediaPlayer mediaPlayer;
    boolean isPlaying = false;
    Handler handler = new Handler();

    int currentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);

        initViews();

        songList = (ArrayList<SongModel>) getIntent()
                .getSerializableExtra("songList");

        currentIndex = getIntent().getIntExtra("position", 0);

        if (songList == null || songList.isEmpty()) {
            finish();
            return;
        }

        upNextRecycler.setLayoutManager(new LinearLayoutManager(this));
        upNextAdapter = new UpNextAdapter(upNextList);
        upNextRecycler.setAdapter(upNextAdapter);

        playSong(currentIndex);
        buildUpNext();

        btnplay.setOnClickListener(v -> togglePlayPause());
        btnNext.setOnClickListener(v -> playNext());
        btnPrevious.setOnClickListener(v -> playPrevious());

        setupSeekBar();
        updateSeekBar();
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

        upNextRecycler = findViewById(R.id.upNextRecycler);
    }

    private void playSong(int index) {

        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }

        SongModel song = songList.get(index);
        int resId = song.getSongResId();

        if (resId == 0) {
            return;
        }

        mediaPlayer = MediaPlayer.create(this, resId);

        if (mediaPlayer == null) {
            return;
        }

        mediaPlayer.start();
        isPlaying = true;

        btnplay.setImageResource(R.drawable.ic_pause);

        musicTitle.setText(song.getTitle());
        musicArtist.setText(song.getArtist());

        songImage.setImageResource(song.getImageResId());

        seekbar.setProgress(0);
        seekbar.setMax(mediaPlayer.getDuration());

        musicstart.setText("0:00");
        musicend.setText(formatTime(mediaPlayer.getDuration()));

        mediaPlayer.setOnCompletionListener(mp -> playNext());
    }

    private void playNext() {
        if (currentIndex < songList.size() - 1) {
            currentIndex++;
            playSong(currentIndex);
            refreshUpNext();
        }
    }

    private void playPrevious() {
        if (currentIndex > 0) {
            currentIndex--;
            playSong(currentIndex);
            refreshUpNext();
        }
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

    private void buildUpNext() {
        upNextList.clear();
        for (int i = currentIndex + 1; i < songList.size(); i++) {
            upNextList.add(songList.get(i));
        }
        upNextAdapter.notifyDataSetChanged();
    }

    private void refreshUpNext() {
        buildUpNext();
    }

    private void setupSeekBar() {
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
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
