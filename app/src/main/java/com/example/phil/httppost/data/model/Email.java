package com.example.phil.httppost.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by phil on 3/20/17.
 */

public class Email {
    public Email(String email){
        this.email = email;
    }

    @SerializedName("email")
    @Expose
    private String email;

}
