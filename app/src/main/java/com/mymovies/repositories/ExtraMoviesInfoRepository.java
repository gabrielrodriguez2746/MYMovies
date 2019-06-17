package com.mymovies.repositories;

import com.mymovies.data.models.Review;
import com.mymovies.data.models.ReviewWrapper;
import com.mymovies.data.models.Trailer;
import com.mymovies.data.models.TrailerWrapper;
import com.mymovies.rest.MoviesService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class ExtraMoviesInfoRepository implements BaseExtraMoviesInfoRepository {

    private MoviesService service;

    @Inject
    public ExtraMoviesInfoRepository(MoviesService service) {
        this.service = service;
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
}
