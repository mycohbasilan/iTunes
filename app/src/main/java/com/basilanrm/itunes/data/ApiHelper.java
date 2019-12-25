package com.basilanrm.itunes.data;

import com.basilanrm.itunes.data.model.MovieListResponse;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

@Singleton
public class ApiHelper {

    public ApiInterface apiInterface;

    @Inject
    ApiHelper(ApiInterface apiInterface) {
        this.apiInterface = apiInterface;
    }

    public Observable<MovieListResponse> getMovies(String term, String country, String media) {
        return apiInterface.getMovies(term,country, media);
    }

}
