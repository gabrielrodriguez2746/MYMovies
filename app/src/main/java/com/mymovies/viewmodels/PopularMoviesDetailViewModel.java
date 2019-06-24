package com.mymovies.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.mymovies.data.models.Movie;
import com.mymovies.repositories.BaseExtraMoviesInfoRepository;
import com.mymovies.repositories.BasePopularMoviesRepository;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class PopularMoviesDetailViewModel extends DetailMoviesViewModel {

    private BasePopularMoviesRepository repository;
    private MutableLiveData<Movie> movieLiveData;

    @Inject
    public PopularMoviesDetailViewModel(BasePopularMoviesRepository repository,
                                        BaseExtraMoviesInfoRepository infoRepository) {
        super(infoRepository);
        this.repository = repository;
        movieLiveData = new MutableLiveData<>();
    }

    @Override
    public void getMovieFromId(int movieId) {
        compositeDisposable.add(
                getAllMovieDataZip(movieId)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                movie -> {
                                }, // Gabriel Improve this
                                Throwable::printStackTrace) // Gabriel Notify
        );
    }

    private Single<Boolean> getAllMovieDataZip(int movieId) {
        return Single.zip(repository.getSingleMovieFromId(movieId)
                        .subscribeOn(AndroidSchedulers.mainThread())
                        .doOnSuccess((result) -> movieLiveData.postValue(result)),
                getReviewsSingle(movieId),
                getFavoritesSingle(movieId),
                getTrailersSingle(movieId), (movie, isFavorite, reviews, trailers) -> true);
    }

    @Override
    public LiveData<Movie> getMovieLiveData() {
        return movieLiveData;
    }
}
