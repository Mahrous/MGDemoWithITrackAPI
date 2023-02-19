package com.mahrous.mgtrackingdemo.data;

public class Tracking {
    public int chargestatus;
    public double latitude;
    public int servertime;
    public int accstatus;
    public int datastatus;
    public int battery;
    public int speed;
    public int hearttime;
    public int doorstatus;
    public String imei;
    public int course;
    public int acctime;
    public int gpstime;
    public int systemtime;
    public int defencestatus;
    public double longitude;
    public int oilpowerstatus;


    public Tracking(int chargestatus, double latitude, int servertime, int accstatus, int datastatus, int battery, int speed, int hearttime, int doorstatus, String imei, int course, int acctime, int gpstime, int systemtime, int defencestatus, double longitude, int oilpowerstatus) {
        this.chargestatus = chargestatus;
        this.latitude = latitude;
        this.servertime = servertime;
        this.accstatus = accstatus;
        this.datastatus = datastatus;
        this.battery = battery;
        this.speed = speed;
        this.hearttime = hearttime;
        this.doorstatus = doorstatus;
        this.imei = imei;
        this.course = course;
        this.acctime = acctime;
        this.gpstime = gpstime;
        this.systemtime = systemtime;
        this.defencestatus = defencestatus;
        this.longitude = longitude;
        this.oilpowerstatus = oilpowerstatus;
    }

    public int getChargestatus() {
        return chargestatus;
    }

    public void setChargestatus(int chargestatus) {
        this.chargestatus = chargestatus;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public int getServertime() {
        return servertime;
    }

    public void setServertime(int servertime) {
        this.servertime = servertime;
    }

    public int getAccstatus() {
        return accstatus;
    }

    public void setAccstatus(int accstatus) {
        this.accstatus = accstatus;
    }

    public int getDatastatus() {
        return datastatus;
    }

    public void setDatastatus(int datastatus) {
        this.datastatus = datastatus;
    }

    public int getBattery() {
        return battery;
    }

    public void setBattery(int battery) {
        this.battery = battery;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getHearttime() {
        return hearttime;
    }

    public void setHearttime(int hearttime) {
        this.hearttime = hearttime;
    }

    public int getDoorstatus() {
        return doorstatus;
    }

    public void setDoorstatus(int doorstatus) {
        this.doorstatus = doorstatus;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public int getAcctime() {
        return acctime;
    }

    public void setAcctime(int acctime) {
        this.acctime = acctime;
    }

    public int getGpstime() {
        return gpstime;
    }

    public void setGpstime(int gpstime) {
        this.gpstime = gpstime;
    }

    public int getSystemtime() {
        return systemtime;
    }

    public void setSystemtime(int systemtime) {
        this.systemtime = systemtime;
    }

    public int getDefencestatus() {
        return defencestatus;
    }

    public void setDefencestatus(int defencestatus) {
        this.defencestatus = defencestatus;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getOilpowerstatus() {
        return oilpowerstatus;
    }

    public void setOilpowerstatus(int oilpowerstatus) {
        this.oilpowerstatus = oilpowerstatus;
    }
}
