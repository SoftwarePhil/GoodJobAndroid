package com.example.phil.httppost.data.remote;


public class ApiUtils {
    private ApiUtils() {}

    public static final String BASE_URL = "http://10.0.2.2:4000/api/";

    public static GoodJobService getAPIService() {

        return RetrofitClient.getClient(BASE_URL).create(GoodJobService.class);
    }
}
