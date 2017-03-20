package com.example.phil.httppost.data.remote;

import com.example.phil.httppost.data.model.Login;
import com.example.phil.httppost.data.model.User;
import com.example.phil.httppost.data.model.Wrap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface GoodJobService {
    @Headers("Content-Type: application/json")
    @POST("job_seeker/login")
    Call<User> login(@Body Login log);

    @Headers("Content-Type: application/json")
    @POST("job_seeker")
    Call<User> create(@Body Wrap wrap);
}


