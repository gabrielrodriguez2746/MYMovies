package com.mymovies.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mymovies.data.models.Movie;
import com.mymovies.repositories.BasePopularMoviesRepository;
import com.mymovies.repositories.BaseTopRatedMoviesRepository;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

public class TopRatedMoviesDetailViewModel extends DetailMoviesViewModel {

    private BaseTopRatedMoviesRepository repository;
    private CompositeDisposable compositeDisposable;
    private MutableLiveData<Movie> movieLiveData;

    @Inject
    public TopRatedMoviesDetailViewModel(BaseTopRatedMoviesRepository repository) {
        this.repository = repository;
        compositeDisposable = new CompositeDisposable();
        movieLiveData = new MutableLiveData<>();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }

    @Override
    public void getMovieFromId(int movieId) {
        compositeDisposable.add(
                repository.getSingleMovieFromId(movieId)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                (result) -> movieLiveData.postValue(result), // TODO This should be mapped here
                                Throwable::printStackTrace) // TODO Notify
        );
    }

    @Override
    public LiveData<Movie> getMovieLiveData() {
        return movieLiveData;
    }
}
