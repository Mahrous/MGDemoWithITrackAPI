package com.mahrous.mgtrackingdemo.network;

public class ApiUtils  {
    private ApiUtils() {
    }

    public static final String BASE_URL = "https://agtechunited.com/mg/";


    public static ApiInterface getAPIService() {
        return RetrofitClient.getClient(BASE_URL).create(ApiInterface.class);
    }



}
