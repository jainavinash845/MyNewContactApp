package com.example.avinash.retrofit;

import com.google.gson.annotations.SerializedName;

public class Owner {

    @SerializedName("avatar_url")
    private String url;



    public String getUrl(){
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
