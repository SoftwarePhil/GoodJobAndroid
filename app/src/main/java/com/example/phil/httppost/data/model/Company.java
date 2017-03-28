package com.example.phil.httppost.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by phil on 3/21/17.
 */

public class Company {
    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("bio")
    @Expose
    private String bio;

    @SerializedName("logo")
    @Expose
    private String logo;

    @SerializedName("link_to_website")
    @Expose
    private String link_to_website;

    @SerializedName("list_of_locations")
    @Expose
    private String[] list_of_locations;

    public String getName() {
        return name;
    }

    public String getBio() {
        return bio;
    }

    public String getLogo() {
        return logo;
    }

    public String getLink_to_website() {
        return link_to_website;
    }

    public String[] getList_of_locations() {
        return list_of_locations;
    }
}
