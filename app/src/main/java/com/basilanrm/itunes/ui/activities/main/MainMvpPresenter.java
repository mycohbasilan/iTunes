package com.basilanrm.itunes.ui.activities.main;

import com.basilanrm.itunes.ui.activities.main.MainView;
import com.basilanrm.itunes.ui.base.Presenter;

public interface MainMvpPresenter<V extends MainView> extends Presenter<V> {
    void getMovies(String term, String country, String media);
}
