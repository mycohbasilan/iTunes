package com.basilanrm.itunes.ui.activities.main;

import com.basilanrm.itunes.data.model.Movie;
import com.basilanrm.itunes.ui.base.BaseMvpView;

import java.util.ArrayList;

public interface MainView extends BaseMvpView {
    void showMovies(ArrayList<Movie> movies);

    void setDateLastVisited(String date);

    void hideLoading();

    void launchMovieDetailsScreen(Movie movie);
}
