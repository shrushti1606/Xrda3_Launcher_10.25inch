package com.xrda3.xrda3_launcher.video;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.xrda3.xrda3_launcher.R;

public class VideoPlayerActivity extends AppCompatActivity {

    private VideoView videoView;
    private ImageButton btnFullscreen, btnLike;
    private boolean isFullscreen = false;
    private boolean isLiked = false;

    private int currentIndex = 0;
    private String videoTitle;

    private FrameLayout.LayoutParams normalParams;
    private FrameLayout.LayoutParams fullscreenParams;

    private final int[] videoList = {
            R.raw.whatsapp_video1,
            R.raw.whatsapp_video2,
            R.raw.whatsapp_video3,
            R.raw.whatsapp_video4,
            R.raw.whatsapp_video5,
            R.raw.whatsapp_video6
    };

    private final String[] titleList = {
            "Beach Top View",
            "Kidntoons",
            "Giggly.Groove",
            "Azzatto.ai",
            "The.Meow.Meow",
            "Shashwat Sachdev"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FrameLayout root = new FrameLayout(this);
        setContentView(root);

        // ================= VideoView =================
        videoView = new VideoView(this);

        normalParams = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.WRAP_CONTENT,
                Gravity.CENTER
        );

        fullscreenParams = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT
        );

        videoView.setLayoutParams(normalParams);
        root.addView(videoView);

        // ================= Title =================
        TextView titleView = new TextView(this);
        FrameLayout.LayoutParams titleParams =
                new FrameLayout.LayoutParams(
                        FrameLayout.LayoutParams.WRAP_CONTENT,
                        FrameLayout.LayoutParams.WRAP_CONTENT,
                        Gravity.TOP | Gravity.CENTER_HORIZONTAL
                );
        titleParams.topMargin = dpToPx(12);
        titleView.setLayoutParams(titleParams);
        titleView.setTextColor(0xFFFFFFFF);
        titleView.setTextSize(18);
        root.addView(titleView);

        // ================= Fullscreen Button (Bottom Right) =================
        btnFullscreen = new ImageButton(this);
        FrameLayout.LayoutParams fsParams =
                new FrameLayout.LayoutParams(dpToPx(48), dpToPx(48),
                        Gravity.BOTTOM | Gravity.END);
        fsParams.setMargins(0, 0, dpToPx(16), dpToPx(50));
        btnFullscreen.setLayoutParams(fsParams);
        btnFullscreen.setBackgroundColor(0x00000000);
        btnFullscreen.setImageResource(R.drawable.fullscreen_icon);
        root.addView(btnFullscreen);

        // ================= Like Button =================
        btnLike = new ImageButton(this);
        FrameLayout.LayoutParams likeParams =
                new FrameLayout.LayoutParams(dpToPx(48), dpToPx(48),
                        Gravity.BOTTOM | Gravity.END);
        likeParams.setMargins(0, 0, dpToPx(16), dpToPx(110));
        btnLike.setLayoutParams(likeParams);
        btnLike.setBackgroundColor(0x00000000);
        root.addView(btnLike);

        // ================= Intent Data =================
        int videoResId = getIntent().getIntExtra("videoResId", videoList[0]);
        videoTitle = getIntent().getStringExtra("videoTitle");
        int resumePosition = getIntent().getIntExtra("resumePosition", 0);

        for (int i = 0; i < videoList.length; i++) {
            if (videoList[i] == videoResId) {
                currentIndex = i;
                break;
            }
        }

        titleView.setText(titleList[currentIndex]);

        isLiked = FavoritesManager.isFavorite(this, videoTitle);
        btnLike.setImageResource(isLiked ? R.drawable.heart_filled : R.drawable.heart_outline);

        playVideo(resumePosition);

        // ================= MediaController with NEXT / PREV =================
        MediaController controller = new MediaController(this);
        controller.setAnchorView(videoView);

        controller.setPrevNextListeners(
                v -> playNext(),   // NEXT
                v -> playPrevious() // PREVIOUS
        );

        videoView.setMediaController(controller);

        // ================= Button Actions =================
        btnFullscreen.setOnClickListener(v -> toggleFullscreen());

        btnLike.setOnClickListener(v -> {
            isLiked = !isLiked;
            if (isLiked) {
                btnLike.setImageResource(R.drawable.heart_filled);
                FavoritesManager.addFavorite(this, videoTitle);
            } else {
                btnLike.setImageResource(R.drawable.heart_outline);
                FavoritesManager.removeFavorite(this, videoTitle);
            }
        });
    }

    // ================= PLAY METHODS =================
    private void playVideo(int seek) {
        videoTitle = titleList[currentIndex];
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + videoList[currentIndex]);
        videoView.setVideoURI(uri);
        videoView.start();
        if (seek > 0) videoView.seekTo(seek);
    }

    private void playNext() {
        if (currentIndex < videoList.length - 1) {
            currentIndex++;
            playVideo(0);
        }
    }

    private void playPrevious() {
        if (currentIndex > 0) {
            currentIndex--;
            playVideo(0);
        }
    }

    // ================= FULLSCREEN =================
    private void toggleFullscreen() {
        if (isFullscreen) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            videoView.setLayoutParams(normalParams);
            btnFullscreen.setImageResource(R.drawable.fullscreen_icon);
        } else {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_FULLSCREEN |
                            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            );
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            videoView.setLayoutParams(fullscreenParams);
            btnFullscreen.setImageResource(R.drawable.fullscreen_exit_icon);
        }
        isFullscreen = !isFullscreen;
    }

    @Override
    protected void onPause() {
        super.onPause();
        int pos = videoView.getCurrentPosition();
        getSharedPreferences("continue_watching", MODE_PRIVATE)
                .edit()
                .putInt(videoTitle, pos)
                .apply();
    }

    private int dpToPx(int dp) {
        return Math.round(dp * getResources().getDisplayMetrics().density);
    }
}
