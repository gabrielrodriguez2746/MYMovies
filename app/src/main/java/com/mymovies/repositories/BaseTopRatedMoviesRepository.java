package com.mymovies.repositories;

import com.mymovies.data.models.Movie;

import java.util.List;

import io.reactivex.Single;

public interface BaseTopRatedMoviesRepository {

    Single<List<Movie>> getTopRatedMoviesByPage(int page);

    Single<Movie> getSingleMovieFromId(int movieId);

}
