package com.example.phil.httppost.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by phil on 4/4/17.
 */

public class JobPreview {
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("company")
    @Expose
    private String company;

    @SerializedName("description")
    @Expose
    private String desciption;

    @SerializedName("tags")
    @Expose
    private String[] tags;

    public String getName() {
        return name;
    }

    public String getCompany() {
        return company;
    }

    public String getDesciption() {
        return desciption;
    }

    public String getId() {
        return id;
    }

    public String[] getTags() {
        return tags;
    }
}

