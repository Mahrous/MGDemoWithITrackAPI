package com.mahrous.mgtrackingdemo.data;

public class AccessTokenGetResponse {
     int code;
     AccessToken record;

    public AccessTokenGetResponse(int code, AccessToken record) {
        this.code = code;
        this.record = record;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public AccessToken getRecord() {
        return record;
    }

    public void setRecord(AccessToken record) {
        this.record = record;
    }
}
