package com.edu.transportation.intelligenttransportation.zhuYeClass;

public class CityUnit {


    /**
     * id : 101011200
     * cityEn : fangshan
     * cityZh : 房山
     * provinceEn : beijing
     * provinceZh : 北京
     * leaderEn : beijing
     * leaderZh : 北京
     * lat : 39.735535
     * lon : 116.139157
     */

    private String id;
    private String cityEn;
    /*区/县*/
    private String cityZh;
    private String provinceEn;
    /*省*/
    private String provinceZh;
    private String leaderEn;
    /*市*/
    private String leaderZh;
    private String lat;
    private String lon;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCityEn() {
        return cityEn;
    }

    public void setCityEn(String cityEn) {
        this.cityEn = cityEn;
    }

    public String getCityZh() {
        return cityZh;
    }

    public void setCityZh(String cityZh) {
        this.cityZh = cityZh;
    }

    public String getProvinceEn() {
        return provinceEn;
    }

    public void setProvinceEn(String provinceEn) {
        this.provinceEn = provinceEn;
    }

    public String getProvinceZh() {
        return provinceZh;
    }

    public void setProvinceZh(String provinceZh) {
        this.provinceZh = provinceZh;
    }

    public String getLeaderEn() {
        return leaderEn;
    }

    public void setLeaderEn(String leaderEn) {
        this.leaderEn = leaderEn;
    }

    public String getLeaderZh() {
        return leaderZh;
    }

    public void setLeaderZh(String leaderZh) {
        this.leaderZh = leaderZh;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }
}
