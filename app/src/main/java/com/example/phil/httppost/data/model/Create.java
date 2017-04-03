package com.example.phil.httppost.data.model;

/**
 * Created by phil on 2/22/17.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Create {

    @SerializedName("tags")
    @Expose
    private String[] tags;
    @SerializedName("resume")
    @Expose
    private String resume;
    @SerializedName("picture")
    @Expose
    private String picture;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("bio")
    @Expose
    private String bio;
    @SerializedName("distance_range")
    @Expose
    private int distance_range;

    public Create(String name, String email, String password, String bio, String resume, String[] tags, int distance, String picture) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.bio = bio;
        this.resume = resume;
        this.tags = tags;
        this.distance_range = distance;
        this.picture = picture;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
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

    public Object getPassword() {
        return password;
    }

    public void setPassword(String chatIds) {
        this.password = chatIds;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public int getDistance() {
        return distance_range;
    }

    public void setDistance(int distance) {
        this.distance_range = distance;
    }

    public String toString(){
        return name + " \n" + bio + "\n" + resume + "\n" + tags + "\n";
    }

}