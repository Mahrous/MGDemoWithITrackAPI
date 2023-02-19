package com.mahrous.mgtrackingdemo.data;

public class Device {

    public String id;
    public String user_id;
    public String account;
    public String password;
    public String serial;
    public String driver_name;
    public String access_token;
    public String latitude;
    public String longitude;
    public String expire_date;

    public Device(String id, String user_id, String account, String password, String serial, String driver_name, String access_token, String latitude, String longitude, String expire_date) {
        this.id = id;
        this.user_id = user_id;
        this.account = account;
        this.password = password;
        this.serial = serial;
        this.driver_name = driver_name;
        this.access_token = access_token;
        this.latitude = latitude;
        this.longitude = longitude;
        this.expire_date = expire_date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getDriver_name() {
        return driver_name;
    }

    public void setDriver_name(String driver_name) {
        this.driver_name = driver_name;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getExpire_date() {
        return expire_date;
    }

    public void setExpire_date(String expire_date) {
        this.expire_date = expire_date;
    }
}


