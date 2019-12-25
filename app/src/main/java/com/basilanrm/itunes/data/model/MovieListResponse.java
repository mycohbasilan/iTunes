package com.basilanrm.itunes.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MovieListResponse {
    @SerializedName("resultCount")
    private int resultCount;
    @SerializedName("results")
    private ArrayList<Movie> results;

    public int getResultCount() {
        return resultCount;
    }

    public void setResultCount(int resultCount) {
        this.resultCount = resultCount;
    }

    public ArrayList<Movie> getResults() {
        return results;
    }

    public void setResults(ArrayList<Movie> results) {
        this.results = results;
    }
}
