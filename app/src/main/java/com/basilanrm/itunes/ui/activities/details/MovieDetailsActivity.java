package com.basilanrm.itunes.ui.activities.details;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.basilanrm.itunes.R;
import com.basilanrm.itunes.constants.Keys;
import com.basilanrm.itunes.data.model.Movie;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetailsActivity extends AppCompatActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_genre)
    TextView tvGenre;
    @BindView(R.id.tv_summary)
    TextView tvSummary;
    @BindView(R.id.iv_poster)
    ImageView ivPoster;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private Movie mMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        ButterKnife.bind(this);
        if (getIntent() != null) {
            mMovie = getIntent().getParcelableExtra(Keys.MOVIE_EXTRA);
        }
        initToolbar();
        tvTitle.setText(mMovie.getTrackName());
        tvGenre.setText(mMovie.getPrimaryGenreName());
        tvSummary.setText(mMovie.getLongDescription());
        Picasso.get()
                .load(mMovie.getArtworkUrl100())
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
                .into(ivPoster);
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
