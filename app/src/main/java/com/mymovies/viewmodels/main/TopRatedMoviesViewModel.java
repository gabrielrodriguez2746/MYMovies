package com.mymovies.viewmodels.main;

import com.mymovies.data.models.Movie;
import com.mymovies.repositories.BaseTopRatedMoviesRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class TopRatedMoviesViewModel extends MoviesListViewModel {

    private BaseTopRatedMoviesRepository repository;

    @Inject
    public TopRatedMoviesViewModel(BaseTopRatedMoviesRepository repository) {
        this.repository = repository;
    }

    @Override
    Single<List<Movie>> getInitialMovies() {
        return repository.getTopRatedMoviesByPage(1);
    }

    @Override
    Single<List<Movie>> getMoreMovies(int page) {
        return repository.getTopRatedMoviesByPage(page);
    }

}
