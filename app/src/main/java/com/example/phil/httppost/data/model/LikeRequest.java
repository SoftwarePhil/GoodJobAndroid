package com.example.phil.httppost.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by phil on 4/8/17.
 */

public class LikeRequest {

    public LikeRequest(String job, String user, String choice){

        this.job = job;
        this.user = user;
        this.choice = choice;
    }

    @SerializedName("job")
    @Expose
    private String job;

    @SerializedName("user")
    @Expose
    private String user;

    @SerializedName("choice")
    @Expose
    private String choice;
}

