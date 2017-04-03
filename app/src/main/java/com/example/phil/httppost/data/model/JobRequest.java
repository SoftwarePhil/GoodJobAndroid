package com.example.phil.httppost.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by phil on 3/20/17.
 */

public class JobRequest {
    public JobRequest(String job){
        this.job = job;
    }

    @SerializedName("job")
    @Expose
    private String job;
}
