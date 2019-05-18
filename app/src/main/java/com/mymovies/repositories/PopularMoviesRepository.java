package com.mymovies.repositories;

import android.annotation.SuppressLint;

import com.mymovies.data.models.Movie;
import com.mymovies.data.models.MovieWrapper;
import com.mymovies.rest.MoviesService;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Maybe;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class PopularMoviesRepository implements BasePopularMoviesRepository {

    private MoviesService service;

    private HashMap<Integer, List<Movie>> moviesCache;

    @SuppressLint("UseSparseArrays")
    @Inject
    public PopularMoviesRepository(MoviesService service) {
        this.service = service;
        moviesCache = new HashMap<>();
    }

    @Override
    public Single<List<Movie>> getPopularMoviesByPage(int page) {
        return getMoviesCache(page)
                .toSingle()
                .flatMap((value) -> validateEmptyPersistence(page, value))
                .onErrorResumeNext((error) -> getDataFromServer(page));
    }

    private Single<List<Movie>> getDataFromServer(int page) {
        return service.getPopular(page)
                .subscribeOn(Schedulers.io())
                .map(MovieWrapper::getMovies)
                .doOnSuccess((movies) -> moviesCache.put(page, movies));

    }

    private Maybe<List<Movie>> getMoviesCache(int key) {
        Maybe<List<Movie>> cachedMaybe = Maybe.empty();
        if (moviesCache.containsKey(key)) {
            List<Movie> cachedValue = moviesCache.get(key);
            if (cachedValue != null && !cachedValue.isEmpty()) {
                cachedMaybe = Maybe.just(cachedValue);
            }
        }
        return cachedMaybe;
    }


    private Single<List<Movie>> validateEmptyPersistence(int page, List<Movie> value) {
        Single<List<Movie>> result;
        if (value.isEmpty()) {
            result = getDataFromServer(page);
        } else {
            result = Single.just(value);
        }
        return result;
    }
}
