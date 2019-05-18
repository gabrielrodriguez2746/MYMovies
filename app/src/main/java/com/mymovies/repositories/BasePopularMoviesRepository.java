package com.mymovies.repositories;

import com.mymovies.data.models.Movie;

import java.util.List;

import io.reactivex.Single;

public interface BasePopularMoviesRepository {

    Single<List<Movie>> getPopularMoviesByPage(int page);

}
