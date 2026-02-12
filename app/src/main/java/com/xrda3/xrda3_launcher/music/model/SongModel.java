package com.xrda3.xrda3_launcher.music.model;

import java.io.Serializable;

public class SongModel implements Serializable {

    private String title;
    private String artist;
    private String duration;
    private int songResId;
    private int imageResId;
    private boolean isFavourite;

    public SongModel(String title, String artist, String duration, int songResId, int imageResId) {
        this.title = title;
        this.artist = artist;
        this.duration = duration;
        this.songResId = songResId;
        this.isFavourite = false;
        this.imageResId = imageResId;

    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getDuration() {
        return duration;
    }

    public int getSongResId() {
        return songResId;
    }

    public boolean isFavourite() {
        return isFavourite;
    }

    public void setFavourite(boolean favourite) {

        isFavourite = favourite;
    }
    public int getImageResId() {
        return imageResId;
    }
}
