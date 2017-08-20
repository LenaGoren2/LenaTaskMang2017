package com.goren.lena.lenataskmang2017.data;

/**
 * Created by user on 12/07/2017.
 */

public class MyTask
{
    private String tKey;
    private String text;
    private boolean isCompleted;
    private long createdAt;
    private double loc_lat;
    private double loc_lng;
    private  String gKey;
    private String uKey;//the group and user keys
    private String address;

    public MyTask() {
    }

    public String gettKey() {
        return tKey;
    }

    public void settKey(String tKey) {
        this.tKey = tKey;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public double getLoc_lat() {
        return loc_lat;
    }

    public void setLoc_lat(double loc_lat) {
        this.loc_lat = loc_lat;
    }

    public double getLoc_lng() {
        return loc_lng;
    }

    public void setLoc_lng(double loc_lng) {
        this.loc_lng = loc_lng;
    }

    public String getGKey() {
        return gKey;
    }

    public void setGKey(String gtKey) {
        this.gKey = gtKey;
    }

    public String getuKey() {
        return uKey;
    }

    public void setuKey(String uKey) {
        this.uKey = uKey;
    }

    @Override
    public String toString() {
        return "MyTask{" +
                "tKey='" + tKey + '\'' +
                ", text='" + text + '\'' +
                ", isCompleted=" + isCompleted +
                ", createdAt=" + createdAt +
                ", loc_lat=" + loc_lat +
                ", loc_lng=" + loc_lng +
                ", gtKey='" + gKey + '\'' +
                ", uKey='" + uKey + '\'' +
                '}';
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
