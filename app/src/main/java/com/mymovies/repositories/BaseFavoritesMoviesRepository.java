package com.mymovies.repositories;

import com.mymovies.data.models.Movie;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

public interface BaseFavoritesMoviesRepository {

    Observable<List<Movie>> getFavoritesMovies();

    Single<Movie> getFavoriteMovieById(int id);

}
