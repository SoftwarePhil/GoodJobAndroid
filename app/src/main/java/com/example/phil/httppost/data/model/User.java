package com.example.phil.httppost.data.model;

/**
 * Created by phil on 2/22/17.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("tags")
    @Expose
    private Object tags;
    @SerializedName("resume")
    @Expose
    private Object resume;
    @SerializedName("picture")
    @Expose
    private String picture;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("chat_ids")
    @Expose
    private Object chatIds;
    @SerializedName("bio")
    @Expose
    private String bio;

    public Object getTags() {
        return tags;
    }

    public void setTags(Object tags) {
        this.tags = tags;
    }

    public Object getResume() {
        return resume;
    }

    public void setResume(Object resume) {
        this.resume = resume;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Object getChatIds() {
        return chatIds;
    }

    public void setChatIds(Object chatIds) {
        this.chatIds = chatIds;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String toString(){
        return name + " \n" + bio + "\n" + resume + "\n" + tags + "\n";
    }

}