package com.example.phil.httppost.data.remote;


public class ApiUtils {
    private ApiUtils() {}

    public static final String BASE_URL = "http://ec2-34-207-144-227.compute-1.amazonaws.com:4000/api/";


    public static GoodJobService getAPIService() {

        return RetrofitClient.getClient(BASE_URL).create(GoodJobService.class);
    }
}
