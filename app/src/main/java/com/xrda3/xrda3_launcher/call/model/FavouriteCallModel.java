package com.xrda3.xrda3_launcher.call.model;

public class FavouriteCallModel {

    private String name;
    private String type;
    private String phone;

    public FavouriteCallModel(String name, String type, String phone) {
        this.name = name;
        this.type = type;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getPhone() {
        return phone;
    }
}
