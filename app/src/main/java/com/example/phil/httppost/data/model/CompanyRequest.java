package com.example.phil.httppost.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by phil on 3/21/17.
 */

public class CompanyRequest {
    public CompanyRequest(String name){
        this.name = name;
    }

    @SerializedName("name")
    @Expose
    private String name;

}
