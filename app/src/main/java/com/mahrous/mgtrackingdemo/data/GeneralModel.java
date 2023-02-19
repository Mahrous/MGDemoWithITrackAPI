package com.mahrous.mgtrackingdemo.data;

public class GeneralModel {
    int statusCode;
    String message;

    public GeneralModel() {
    }

    public GeneralModel(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
