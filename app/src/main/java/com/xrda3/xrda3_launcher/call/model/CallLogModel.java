package com.xrda3.xrda3_launcher.call.model;

public class CallLogModel {
    private String name;
    private String info;
    private String phone;
    private int type;

    public CallLogModel(String name, String info, String phone, int type) {
        this.name = name;
        this.info = info;
        this.phone = phone;
        this.type = type;
    }

    public String getName() { return name; }
    public String getInfo() { return info; }
    public String getPhone() { return phone; }
    public int getType() { return type; }
}
