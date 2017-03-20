package com.example.phil.httppost.data.remote;

import com.example.phil.httppost.data.model.Login;
import com.example.phil.httppost.data.model.User;
import com.example.phil.httppost.data.model.Wrap;
import com.example.phil.httppost.data.model.Email;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface GoodJobService {
    @Headers("Content-Type: application/json")
    @POST("job_seeker/login")
    Call<User> login(@Body Login log);

    @Headers("Content-Type: application/json")
    @POST("job_seeker")
    Call<User> create(@Body Wrap wrap);

    @Headers("Content-Type: application/json")
    @POST("job/job_feed")
    Call<ArrayList> jobList(@Body Email email);
}


