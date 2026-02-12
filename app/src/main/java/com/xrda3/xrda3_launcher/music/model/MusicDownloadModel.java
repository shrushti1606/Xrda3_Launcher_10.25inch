package com.xrda3.xrda3_launcher.music.model;

public class MusicDownloadModel {

    private String name;
    private String artist;
    private String duration;
    private int albumImage;
    private int songResId;

    public MusicDownloadModel(String name, String artist, String duration, int albumImage, int songResId) {
        this.name = name;
        this.artist = artist;
        this.duration = duration;
        this.albumImage = albumImage;
        this.songResId = songResId;
    }

    public String getName() {
        return name;
    }

    public String getArtist() {
        return artist;
    }

    public String getDuration() {
        return duration;
    }

    public int getAlbumImage() {
        return albumImage;
    }

    public int getSongResId() {
        return songResId;
    }
}
