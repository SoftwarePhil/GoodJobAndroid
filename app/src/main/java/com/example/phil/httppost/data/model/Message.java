package com.example.phil.httppost.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by phil on 3/20/17.
 */

public class Message {
    public Message(String sender_name, String sender, String content){
        this.content = content;
        this.sender = sender;
        this.sender_name = sender_name;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getSender_name() {
        return sender_name;
    }

    public String getSender() {
        return sender;
    }

    public String getContent() {
        return content;
    }

    @SerializedName("timestamp")
    @Expose
    private String timestamp;

    @SerializedName("sender_name")
    @Expose
    private String sender_name;

    @SerializedName("sender")
    @Expose
    private String sender;

    @SerializedName("content")
    @Expose
    private String content;
}
