package com.basilanrm.itunes.ui.activities.main;

import android.content.Context;

import com.basilanrm.itunes.constants.Constants;
import com.basilanrm.itunes.data.DataManager;
import com.basilanrm.itunes.data.model.Movie;
import com.basilanrm.itunes.data.model.MovieListResponse;
import com.basilanrm.itunes.di.AppContext;
import com.basilanrm.itunes.ui.base.BasePresenter;
import com.basilanrm.itunes.ui.base.BaseSubscriber;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class MainPresenter<V extends MainView> extends BasePresenter<V> implements MainMvpPresenter<V> {

    private DataManager dataManager;
    private ArrayList<Movie> movieList;

    @Inject
    public MainPresenter(@AppContext Context context, DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(context, compositeDisposable);
        this.dataManager = dataManager;
    }

    @Override
    public void getMovies(String term, String country, String media) {
        getMVPView().setDateLastVisited(dataManager.getLastVisitedDate());
        dataManager.setLastVisitedDate(getCurrentDate());
        observe(dataManager.getMovies(term, country, media),
                new BaseSubscriber<MovieListResponse>() {
                    @Override
                    public void onSuccess(MovieListResponse movieListResponse) {
                        movieList = movieListResponse.getResults();
                        getMVPView().hideLoading();
                        getMVPView().showMovies(movieList);
                    }

                    @Override
                    public void onError(String error) {
                        getMVPView().hideLoading();
                        getMVPView().showError(error);
                    }

                });
    }

    private String getCurrentDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.DATE_FORMAT);
        return simpleDateFormat.format(new Date());
    }
}
