package com.basilanrm.itunes.ui.activities.main;

import android.content.Context;

import com.basilanrm.itunes.constants.Constants;
import com.basilanrm.itunes.data.DataManager;
import com.basilanrm.itunes.data.model.Movie;
import com.basilanrm.itunes.data.model.MovieListResponse;
import com.basilanrm.itunes.di.AppContext;
import com.basilanrm.itunes.ui.base.BasePresenter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter <V extends MainView> extends BasePresenter<V> implements MainMvpPresenter<V> {

    private DataManager dataManager;
    private ArrayList<Movie> movieList;

    @Inject
    public MainPresenter(@AppContext Context context, DataManager dataManager) {
        super(context);
        this.dataManager = dataManager;
    }

    @Override
    public void getMovies(String term, String country, String media) {
        getMVpView().setDateLastVisited(dataManager.getLastVisitedDate());
        dataManager.setLastVisitedDate(getCurrentDate());
        dataManager.getMovies(term, country, media)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MovieListResponse>() {
                    Disposable disposable;
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(MovieListResponse results) {
                        movieList = results.getResults();
                        getMVpView().hideLoading();
                        getMVpView().showMovies(movieList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getMVpView().hideLoading();
                        getMVpView().showError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        if(!disposable.isDisposed()){
                            disposable.dispose();
                        }
                    }
                });
    }

    private String getCurrentDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.DATE_FORMAT);
        return simpleDateFormat.format(new Date());
    }
}
