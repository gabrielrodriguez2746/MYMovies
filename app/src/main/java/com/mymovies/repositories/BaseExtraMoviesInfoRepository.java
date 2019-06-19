package com.mymovies.repositories;

import com.mymovies.data.models.Movie;
import com.mymovies.data.models.Review;
import com.mymovies.data.models.Trailer;

import java.util.List;

import io.reactivex.Single;

public interface BaseExtraMoviesInfoRepository {

    Single<List<Trailer>> getTrailersById(int id);

    Single<List<Review>> getReviewsById(int id);

    Single<Boolean> getFavoriteMovieById(int id);

    Single<Boolean> updateFavoriteMovie(Movie movie);
}
