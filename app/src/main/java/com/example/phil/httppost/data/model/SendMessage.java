package com.example.phil.httppost.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by phil on 4/6/17.
 */
//{"sender":"se.phildimarco@gmail.com", "job_seeker":"se.phildimarco@gmail.com", "job":"Evil Corp&paper boy", "content":"hey 1!!"}
public class SendMessage {

    public SendMessage(String sender, String sender_name, String job_seeker, String job, String content){
        this.sender = sender;
        this.sender_name = sender_name;
        this.job_seeker = job_seeker;
        this.job = job;
        this.content = content;
    }

    public String getjob_seeker() {
        return job_seeker;
    }

    public String getJob() {
        return job;
    }

    public String getSender() {
        return sender;
    }

    public String getContent() {
        return content;
    }

    public String getsender_name() {
        return sender_name;
    }

    @SerializedName("job_seeker")
    @Expose
    private String job_seeker;

    @SerializedName("job")
    @Expose
    private String job;

    @SerializedName("sender")
    @Expose
    private String sender;

    @SerializedName("sender_name")
    @Expose
    private String sender_name;

    @SerializedName("content")
    @Expose
    private String content;
}
