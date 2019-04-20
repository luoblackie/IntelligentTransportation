package com.edu.transportation.intelligenttransportation.adapter;

//卡片值对象
public class CardInfo {

    private String title;
    private int imgId;

    public CardInfo(String title, int imgId) {
        this.title = title;
        this.imgId = imgId;
    }

    public String getTitle() {
        return title;
    }

    public int getImgId() {
        return imgId;
    }
}
