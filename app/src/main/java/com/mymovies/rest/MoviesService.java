package com.mymovies.rest;

import com.mymovies.data.models.MovieWrapper;
import com.mymovies.data.models.ReviewWrapper;
import com.mymovies.data.models.TrailerWrapper;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MoviesService {

    @GET("movie/popular")
    Single<MovieWrapper> getPopular(@Query("page") int page);

    @GET("movie/top_rated")
    Single<MovieWrapper> getTopRated(@Query("page") int page);

    @GET("movie/{id}/videos")
    Single<TrailerWrapper> getTrailers(@Path("id") int id);

    @GET("movie/{id}/reviews")
    Single<ReviewWrapper> getReviews(@Path("id") int id);
}
