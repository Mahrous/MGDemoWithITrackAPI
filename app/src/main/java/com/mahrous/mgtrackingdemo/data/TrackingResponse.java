package com.mahrous.mgtrackingdemo.data;

import java.util.ArrayList;

public class TrackingResponse {
    public int code;
    public ArrayList<Tracking> record;

    public TrackingResponse(int code, ArrayList<Tracking> record) {
        this.code = code;
        this.record = record;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ArrayList<Tracking> getRecord() {
        return record;
    }

    public void setRecord(ArrayList<Tracking> record) {
        this.record = record;
    }
}
