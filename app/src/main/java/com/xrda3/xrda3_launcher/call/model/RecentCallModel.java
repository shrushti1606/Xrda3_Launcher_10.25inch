package com.xrda3.xrda3_launcher.call.model;

public class RecentCallModel {
    private String name;
    private String info;
    private String phone; // add phone for calling

    public RecentCallModel(String name, String info, String phone) {
        this.name = name;
        this.info = info;
        this.phone = phone;
    }

    public String getName() { return name; }
    public String getInfo() { return info; }
    public String getPhone() { return phone; }
}
