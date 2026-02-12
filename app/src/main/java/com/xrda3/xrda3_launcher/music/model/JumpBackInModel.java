package com.xrda3.xrda3_launcher.music.model;

public class JumpBackInModel {

    private String title;
    private int imageRes;
    private String pageType;

    public JumpBackInModel(String title, int imageRes, String pageType) {
        this.title = title;
        this.imageRes = imageRes;
        this.pageType = pageType;
    }

    public String getTitle() {
        return title;
    }

    public int getImageRes() {
        return imageRes;
    }

    public String getPageType() {
        return pageType;
    }
}
