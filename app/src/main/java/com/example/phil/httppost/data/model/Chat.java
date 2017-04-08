package com.example.phil.httppost.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by phil on 3/20/17.
 */

public class Chat {
    @SerializedName("job_seeker")
    @Expose
    private String job_seeker;

    @SerializedName("job")
    @Expose
    private String job;

    @SerializedName("messages")
    @Expose
    private ArrayList<Message> messages;

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public String getJob() {
        return job;
    }

    public String getJob_seeker() {
        return job_seeker;
    }
}


