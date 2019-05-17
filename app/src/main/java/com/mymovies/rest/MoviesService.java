package com.mymovies.rest;

import com.mymovies.data.models.MovieWrapper;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MoviesService {

    @GET("movie/popular")
    Single<MovieWrapper> getPopular(@Query("page") int  page);

    @GET("movie/top_rated")
    Single<MovieWrapper> getTopRated(@Query("page") int  page);
}
