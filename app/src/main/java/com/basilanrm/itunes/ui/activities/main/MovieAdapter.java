package com.basilanrm.itunes.ui.activities.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.basilanrm.itunes.R;
import com.basilanrm.itunes.data.model.Movie;
import com.basilanrm.itunes.ui.ViewCallback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> implements ViewCallback {
    private Context mContext;
    private ArrayList<Movie> mMovieList;
    private MainView mMainView;

    public MovieAdapter(Context mContext, ArrayList<Movie> mMovieList) {
        this.mContext = mContext;
        this.mMovieList = mMovieList;
    }

    @NonNull
    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.ViewHolder holder, int position) {
        holder.bind(mMovieList.get(position));
    }

    @Override
    public int getItemCount() {
        return mMovieList.size();
    }

    @Override
    public void setMainView(MainView view) {
        this.mMainView = view;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_movie_poster)
        ImageView ivMovie;
        @BindView(R.id.item_movie_title)
        TextView tvTitle;
        @BindView(R.id.item_movie_price)
        TextView tvPrice;
        @BindView(R.id.item_movie_genre)
        TextView tvGenre;

        private Movie mMovie;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(v -> mMainView.launchMovieDetailsScreen(mMovie));
        }

        public void bind(Movie movie) {
            tvTitle.setText(movie.getTrackName());
            tvPrice.setText("$" + movie.getTrackPrice());
            tvGenre.setText(movie.getPrimaryGenreName());
            Picasso.get()
                    .load(movie.getArtworkUrl100())
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .error(R.drawable.ic_launcher_foreground)
                    .into(ivMovie);
            this.mMovie = movie;
        }

    }
}
