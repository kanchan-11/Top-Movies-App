package com.example.topmovies;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

@Data
public class MovieNetworkResponse {
    @SerializedName("page") Integer page;
    @SerializedName("total_results") Integer totalresults;
    @SerializedName("total_pages") Integer totalpages;
    @SerializedName("results")
    List<Movie> movies;
}
