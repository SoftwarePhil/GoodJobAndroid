package com.example.phil.httppost.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by phil on 4/8/17.
 */

public class GoodResponse {

    public String getOk(){
        return ok;
    }

    @SerializedName("ok")
    @Expose
    private String ok;

    public String getMessage(){
        return ok;
    }
}
