package com.mymovies.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mymovies.data.models.Movie;
import com.mymovies.data.models.Review;
import com.mymovies.data.models.Trailer;
import com.mymovies.repositories.BaseExtraMoviesInfoRepository;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;

public abstract class DetailMoviesViewModel extends ViewModel {

    protected MutableLiveData<Movie> movieLiveData;
    protected MutableLiveData<List<Review>> reviewsLiveData;
    protected MutableLiveData<List<Trailer>> trailersLiveData;
    private BaseExtraMoviesInfoRepository infoRepository;

    public DetailMoviesViewModel(BaseExtraMoviesInfoRepository infoRepository) {
        this.infoRepository = infoRepository;
        movieLiveData = new MutableLiveData<>();
        trailersLiveData = new MutableLiveData<>();
        reviewsLiveData = new MutableLiveData<>();
    }

    protected Single<List<Review>> getReviewsSingle(int id) {
        return infoRepository.getReviewsById(id)
                .subscribeOn(AndroidSchedulers.mainThread())
                .doOnSuccess(reviews -> reviewsLiveData.postValue(reviews));
    }

    protected Single<List<Trailer>> getTrailersSingle(int id) {
        return infoRepository.getTrailersById(id)
                .subscribeOn(AndroidSchedulers.mainThread())
                .doOnSuccess(trailers -> trailersLiveData.postValue(trailers));
    }

    public abstract void getMovieFromId(int movieId);

    public LiveData<Movie> getMovieLiveData() {
        return movieLiveData;
    }

    public LiveData<List<Review>> getReviewsLiveData() {
        return reviewsLiveData;
    }

    public LiveData<List<Trailer>> getTrailersLiveData() {
        return trailersLiveData;
    }
}
