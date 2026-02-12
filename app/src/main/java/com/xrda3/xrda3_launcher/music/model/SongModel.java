package com.xrda3.xrda3_launcher.music.model;

public class SongModel {

    private String title;
    private String artist;
    private String duration;
    private int songResId;
    private boolean isFavourite;


    public SongModel(String title, String artist, String duration, int songResId) {
        this.title = title;
        this.artist = artist;
        this.duration = duration;
        this.songResId = songResId;
        this.isFavourite = false;
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
}
