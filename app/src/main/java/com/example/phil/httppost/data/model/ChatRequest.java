package com.example.phil.httppost.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by phil on 3/20/17.
 */

public class ChatRequest {
    public ChatRequest(String job_seeker, String job){
        this.job_seeker = job_seeker;
        this.job = job;
    }

    @SerializedName("job_seeker")
    @Expose
    private String job_seeker;

    @SerializedName("job")
    @Expose
    private String job;

}
