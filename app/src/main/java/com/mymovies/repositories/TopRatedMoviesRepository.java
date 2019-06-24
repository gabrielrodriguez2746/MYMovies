package com.mymovies.repositories;

import android.annotation.SuppressLint;

import com.mymovies.data.models.Movie;
import com.mymovies.data.models.MovieWrapper;
import com.mymovies.rest.MoviesService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Maybe;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class TopRatedMoviesRepository implements BaseTopRatedMoviesRepository {

    private MoviesService service;

    private HashMap<Integer, List<Movie>> moviesCache;

    @SuppressLint("UseSparseArrays")
    @Inject
    public TopRatedMoviesRepository(MoviesService service) {
        this.service = service;
        moviesCache = new HashMap<>();
    }

    @Override
    public Single<List<Movie>> getTopRatedMoviesByPage(int page) {
        return getMoviesCache(page)
                .toSingle()
                .flatMap((value) -> validateEmptyPersistence(page, value))
                .onErrorResumeNext((error) -> getDataFromServer(page));
    }

    @Override
    public Single<Movie> getSingleMovieFromId(int movieId) {
        return getAllMoviesSingle()
                .subscribeOn(Schedulers.computation())
                .map(movies -> {
                    Movie searchedMovie = null;
                    for (Movie movie : movies) {
                        if (movie.getId() == movieId) {
                            searchedMovie = movie;
                            break;
                        }
                    }
                    return searchedMovie;
                });
//                .onErrorResumeNext(); Gabriel Need to add also page information for this in save instance case
    }

    private Single<List<Movie>> getAllMoviesSingle() {
        return Single.fromCallable(() -> {
            ArrayList<Movie> movies = new ArrayList<>();
            for (Map.Entry<Integer, List<Movie>> entrySet : moviesCache.entrySet()) {
                movies.addAll(entrySet.getValue());
            }
            return movies;
        });
    }

    private Single<List<Movie>> getDataFromServer(int page) {
        return service.getTopRated(page)
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
