package com.edu.transportation.intelligenttransportation.BaseClass;

public class Permission {
    private String permission;
    private String title;
    private String reason;

    public Permission(String permission, String title, String reason) {
        this.permission = permission;
        this.title = title;
        this.reason = reason;
    }

    public String getPermission() {
        return permission;
    }

    public String getTitle() {
        return title;
    }

    public String getReason() {
        return reason;
    }
}
