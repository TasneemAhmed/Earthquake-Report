package com.example.android.quakereport;

public class Earthquake {

    private double magntitude;
    private String location;
    private long date;
    private String url;

    public Earthquake(double magntitude, String location, long date, String url) {
        this.magntitude = magntitude;
        this.location = location;
        this.date = date;
        this.url = url;
    }



    public String getLocation() {
        return location;
    }

    public double getMagntitude() {
        return magntitude;
    }

    public long getDate() {
        return date;
    }

    public String getUrl() {
        return url;
    }
}
