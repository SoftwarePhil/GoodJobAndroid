package com.example.phil.httppost.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by phil on 3/19/17.
 */

public class Wrap {
    @SerializedName("create")
    @Expose
    private Create create;

    public Wrap(Create create){
        this.create = create;
    }

    public Create getCreate() {
        return create;
    }

    public void setCreate(Create create) {
        this.create = create;
    }
}
