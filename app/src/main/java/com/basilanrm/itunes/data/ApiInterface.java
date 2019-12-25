package com.basilanrm.itunes.data;

import com.basilanrm.itunes.data.model.MovieListResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("search")
    Observable<MovieListResponse> getMovies(@Query("term") String term,
                                            @Query("country") String country,
                                            @Query("media") String media);
}
