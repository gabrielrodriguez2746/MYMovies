package com.mymovies.viewmodels.main;

import com.mymovies.data.models.Movie;
import com.mymovies.repositories.BasePopularMoviesRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class PopularMoviesViewModel extends MoviesListViewModel {

    private BasePopularMoviesRepository repository;

    @Inject
    public PopularMoviesViewModel(BasePopularMoviesRepository repository) {
        super();
        this.repository = repository;
    }

    @Override
    Single<List<Movie>> getInitialMovies() {
        return repository.getPopularMoviesByPage(1);
    }

    @Override
    Single<List<Movie>> getMoreMovies(int page) {
        return repository.getPopularMoviesByPage(page);
    }

}
