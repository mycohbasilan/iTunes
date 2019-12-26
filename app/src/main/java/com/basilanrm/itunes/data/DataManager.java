package com.basilanrm.itunes.data;

import com.basilanrm.itunes.data.model.MovieListResponse;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

@Singleton
public class DataManager {

    ApiHelper apiHelper;
    PrefsHelper prefsHelper;

    @Inject
    public DataManager(ApiHelper apiHelper, PrefsHelper prefsHelper) {
        this.apiHelper = apiHelper;
        this.prefsHelper = prefsHelper;
    }

    public Observable<MovieListResponse> getMovies(String term, String country, String media) {
        return apiHelper.getMovies(term, country, media);
    }

    public String getLastVisitedDate() {
        return prefsHelper.getLastVisitedDate();
    }

    public void setLastVisitedDate(String date) {
        prefsHelper.setLastVisitedDate(date);
    }
}
