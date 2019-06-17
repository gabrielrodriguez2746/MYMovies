package com.mymovies.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.mymovies.data.models.Movie;
import com.mymovies.data.models.Review;
import com.mymovies.data.models.Trailer;
import com.mymovies.repositories.BaseExtraMoviesInfoRepository;
import com.mymovies.repositories.BasePopularMoviesRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function3;

public class PopularMoviesDetailViewModel extends DetailMoviesViewModel {

    private BasePopularMoviesRepository repository;
    private CompositeDisposable compositeDisposable;
    private MutableLiveData<Movie> movieLiveData;

    @Inject
    public PopularMoviesDetailViewModel(BasePopularMoviesRepository repository,
                                        BaseExtraMoviesInfoRepository infoRepository) {
        super(infoRepository);
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
                getAllMovieDataZip(movieId)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                movie -> { }, // TODO Improve this
                                Throwable::printStackTrace) // TODO Notify
        );
    }

    private Single<Boolean> getAllMovieDataZip(int movieId) {
        return Single.zip(repository.getSingleMovieFromId(movieId)
                        .subscribeOn(AndroidSchedulers.mainThread())
                        .doOnSuccess((result) -> movieLiveData.postValue(result)),
                getReviewsSingle(movieId),
                getTrailersSingle(movieId), (movie, reviews, trailers) -> true);
    }

    @Override
    public LiveData<Movie> getMovieLiveData() {
        return movieLiveData;
    }
}
