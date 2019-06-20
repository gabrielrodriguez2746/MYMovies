package com.mymovies.repositories;

import com.mymovies.data.dao.FavoritesMoviesDao;
import com.mymovies.data.models.Movie;
import com.mymovies.data.models.Review;
import com.mymovies.data.models.ReviewWrapper;
import com.mymovies.data.models.Trailer;
import com.mymovies.data.models.TrailerWrapper;
import com.mymovies.rest.MoviesService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class ExtraMoviesInfoRepository implements BaseExtraMoviesInfoRepository {

    private MoviesService service;
    private FavoritesMoviesDao favoritesMoviesDao;

    @Inject
    public ExtraMoviesInfoRepository(MoviesService service,
                                     FavoritesMoviesDao favoritesMoviesDao) {
        this.service = service;
        this.favoritesMoviesDao = favoritesMoviesDao;
    }

    @Override
    public Single<List<Trailer>> getTrailersById(int id) {
        return service.getTrailers(id)
                .subscribeOn(Schedulers.io())
                .map(TrailerWrapper::getTrailers);
    }

    @Override
    public Single<List<Review>> getReviewsById(int id) {
        return service.getReviews(id)
                .subscribeOn(Schedulers.io())
                .map(ReviewWrapper::getReviews);
    }

    @Override
    public Single<Boolean> getFavoriteMovieById(int id) {
        return favoritesMoviesDao.getMovieById(id)
                .subscribeOn(Schedulers.io())
                .toSingle()
                .map(movie -> true)
                .onErrorReturnItem(false);
    }

    @Override
    public Single<Boolean> updateFavoriteMovie(Movie movie) {
        return favoritesMoviesDao.getMovieById(movie.getId())
                .subscribeOn(Schedulers.io())
                .toSingle()
                .flatMap((Function<Movie, SingleSource<Boolean>>) persistenceMovie -> removeFavorite(movie.getId()))
                .onErrorResumeNext(throwable -> insertNewFavorite(movie));
    }

    private Single<Boolean> insertNewFavorite(Movie movie) {
        return favoritesMoviesDao.insert(movie)
                .subscribeOn(Schedulers.io())
                .toSingleDefault(true)
                .onErrorReturnItem(false);
    }

    private Single<Boolean> removeFavorite(int id) {
        return favoritesMoviesDao.deleteMovieById(id)
                .subscribeOn(Schedulers.io())
                .toSingleDefault(false)
                .onErrorReturnItem(true);
    }
}
