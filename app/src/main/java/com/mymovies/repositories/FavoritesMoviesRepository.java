package com.mymovies.repositories;

import com.mymovies.data.dao.FavoritesMoviesDao;
import com.mymovies.data.models.Movie;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class FavoritesMoviesRepository implements BaseFavoritesMoviesRepository {

    private FavoritesMoviesDao favoritesMoviesDao;

    @Inject
    public FavoritesMoviesRepository(FavoritesMoviesDao favoritesMoviesDao) {
        this.favoritesMoviesDao = favoritesMoviesDao;
    }

    @Override
    public Observable<List<Movie>> getFavoritesMovies() {
        return favoritesMoviesDao.getFavoritesMovies()
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Single<Movie> getFavoriteMovieById(int id) {
        return favoritesMoviesDao.getMovieById(id)
                .subscribeOn(Schedulers.io())
                .toSingle();
    }
}
