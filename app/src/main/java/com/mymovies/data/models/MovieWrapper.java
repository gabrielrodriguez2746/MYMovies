package com.mymovies.data.models;

import java.util.List;

public class MovieWrapper {

    private List<Movie> movies;

    public MovieWrapper(List<Movie> movies) {
        this.movies = movies;
    }

    public List<Movie> getMovies() {
        return movies;
    }
}
