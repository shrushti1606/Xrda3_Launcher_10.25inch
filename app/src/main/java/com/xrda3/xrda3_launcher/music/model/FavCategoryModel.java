package com.xrda3.xrda3_launcher.music.model;

public class FavCategoryModel {

    private String title;
    private String description;

    public FavCategoryModel(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}

