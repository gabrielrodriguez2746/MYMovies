package com.mymovies.viewmodels;

import com.mymovies.repositories.BaseExtraMoviesInfoRepository;
import com.mymovies.repositories.BaseTopRatedMoviesRepository;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class TopRatedMoviesDetailViewModel extends DetailMoviesViewModel {

    private BaseTopRatedMoviesRepository repository;

    @Inject
    public TopRatedMoviesDetailViewModel(BaseTopRatedMoviesRepository repository,
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
                                }, // TODO Improve this
                                Throwable::printStackTrace) // TODO Notify
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
}
