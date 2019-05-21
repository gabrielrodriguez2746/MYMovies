package com.mymovies.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.mymovies.data.models.Movie;

public abstract class DetailMoviesViewModel extends ViewModel {

    public abstract void getMovieFromId(int movieId);

    public abstract LiveData<Movie> getMovieLiveData();
}
