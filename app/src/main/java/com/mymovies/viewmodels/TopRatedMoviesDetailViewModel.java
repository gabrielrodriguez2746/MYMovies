package com.mymovies.viewmodels;

import com.mymovies.repositories.BaseExtraMoviesInfoRepository;
import com.mymovies.repositories.BaseTopRatedMoviesRepository;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

public class TopRatedMoviesDetailViewModel extends DetailMoviesViewModel {

    private BaseTopRatedMoviesRepository repository;
    private CompositeDisposable compositeDisposable;

    @Inject
    public TopRatedMoviesDetailViewModel(BaseTopRatedMoviesRepository repository,
                                         BaseExtraMoviesInfoRepository infoRepository) {
        super(infoRepository);
        this.repository = repository;
        compositeDisposable = new CompositeDisposable();
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
                                movie -> {
                                }, // TODO Improve this
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
}
