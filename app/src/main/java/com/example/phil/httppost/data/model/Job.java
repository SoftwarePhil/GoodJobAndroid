package com.example.phil.httppost.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by phil on 3/20/17.
 */

public class Job {
    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("company")
    @Expose
    private String company;

    @SerializedName("description")
    @Expose
    private String desciption;

    @SerializedName("post_date")
    @Expose
    private String post_date;

    @SerializedName("salary_range")
    @Expose
    private String salary_range;

    @SerializedName("employment_type")
    @Expose
    private String employment_type;

    @SerializedName("location")
    @Expose
    private String location;

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

    public String getPost_date() {
        return post_date;
    }

    public String getSalary_range() {
        return salary_range;
    }

    public String getEmployment_type() {
        return employment_type;
    }

    public String getLocation() {
        return location;
    }

    public String[] getTags() {
        return tags;
    }
}
