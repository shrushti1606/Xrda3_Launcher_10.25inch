package com.xrda3.xrda3_launcher.video.model;

import android.net.Uri;    // loaction of the video is stored

public class VideoModel {   //creating a data container for one video
                            // this class only stores datat
    public String title;
    public Uri videoUri;

    public VideoModel(String title, Uri videoUri) {
        this.title = title;
        this.videoUri = videoUri;
    }
}
