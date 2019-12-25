package com.basilanrm.itunes.data;

import com.basilanrm.itunes.data.model.MovieListResponse;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

@Singleton
public class DataManager {
    ApiHelper apiHelper;

    @Inject
    public DataManager(ApiHelper apiHelper) {
        this.apiHelper = apiHelper;
    }

    public Observable<MovieListResponse> getMovies(String term, String country, String media) {
        return apiHelper.getMovies(term, country, media);
    }
}
