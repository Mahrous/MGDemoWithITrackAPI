package com.mahrous.mgtrackingdemo.data;

public class UserResponse {
    boolean succeeded;
    String message ;
    int id;
    String username;
    String email;
    String mobile;

    public UserResponse(boolean succeeded, int id, String username, String email, String mobile) {
        this.succeeded = succeeded;
        this.id = id;
        this.username = username;
        this.email = email;
        this.mobile = mobile;
    }

    public UserResponse(boolean succeeded, String message, int id, String username, String email, String mobile) {
        this.succeeded = succeeded;
        this.message = message;
        this.id = id;
        this.username = username;
        this.email = email;
        this.mobile = mobile;
    }

    public boolean isSucceeded() {
        return succeeded;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSucceeded(boolean succeeded) {
        this.succeeded = succeeded;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
