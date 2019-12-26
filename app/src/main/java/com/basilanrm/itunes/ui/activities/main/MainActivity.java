package com.basilanrm.itunes.ui.activities.main;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.basilanrm.itunes.R;
import com.basilanrm.itunes.data.model.Movie;
import com.basilanrm.itunes.ui.base.BaseActivity;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements MainView {

    private static final String TERM = "star";
    private static final String COUNTRY = "au";
    private static final String MEDIA = "movie";

    @Inject
    MainMvpPresenter<MainView> mainMvpPresenter;
    @BindView(R.id.rv_movie_list)
    RecyclerView rvMovies;
    private MovieAdapter mMovieAdapter;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_datelastvisited)
    TextView tvLastVisitedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getActivityComponent().inject(this);
        ButterKnife.bind(this);
        mainMvpPresenter.attachView(this);
        mainMvpPresenter.getMovies(TERM, COUNTRY, MEDIA);
        rvMovies.setLayoutManager(new LinearLayoutManager(this));
        toolbar.setTitle(getResources().getString(R.string.movies));
    }

    @Override
    public void showMovies(ArrayList<Movie> movies) {
        mMovieAdapter = new MovieAdapter(this, movies);
        rvMovies.setAdapter(mMovieAdapter);
    }

    @Override
    public void setDateLastVisited(String date) {
        tvLastVisitedDate.setText(getResources().getString(R.string.last_visited_date, date));
    }
}
