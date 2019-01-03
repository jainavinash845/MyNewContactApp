package com.example.avinash.retrofit;

import android.view.View;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("/users/{user}/repos")
    Call<List<Repo>> getRepo(@Path("user") String user );



}
