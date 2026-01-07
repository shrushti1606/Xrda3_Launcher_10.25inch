package com.xrda3.xrda3_launcher.video.model;

public class VideoModel {

    private String id;
    private String uri;
    private String title;
    private String duration;
    private String folder;

    public VideoModel(String id, String uri, String title, String duration, String folder) {
        this.id = id;
        this.uri = uri;
        this.title = title;
        this.duration = duration;
        this.folder = folder;
    }

    public String getId() { return id; }
    public String getUri() { return uri; }
    public String getTitle() { return title; }
    public String getDuration() { return duration; }
    public String getFolder() { return folder; }
}
