package com.mymovies.viewmodels;

import com.mymovies.repositories.BaseExtraMoviesInfoRepository;
import com.mymovies.repositories.BaseFavoritesMoviesRepository;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class FavoritesMoviesDetailViewModel extends DetailMoviesViewModel {

    private BaseFavoritesMoviesRepository repository;

    @Inject
    public FavoritesMoviesDetailViewModel(BaseFavoritesMoviesRepository repository,
                                          BaseExtraMoviesInfoRepository infoRepository) {
        super(infoRepository);
        this.repository = repository;
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
        return Single.zip(repository.getFavoriteMovieById(movieId)
                        .subscribeOn(AndroidSchedulers.mainThread())
                        .doOnSuccess((result) -> movieLiveData.postValue(result)),
                getReviewsSingle(movieId),
                getFavoritesSingle(movieId),
                getTrailersSingle(movieId), (movie, isFavorite, reviews, trailers) -> true);
    }
}
