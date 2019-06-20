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
import io.reactivex.disposables.CompositeDisposable;

public abstract class DetailMoviesViewModel extends ViewModel {

    protected MutableLiveData<Movie> movieLiveData;
    protected MutableLiveData<List<Review>> reviewsLiveData;
    protected MutableLiveData<List<Trailer>> trailersLiveData;
    protected MutableLiveData<Boolean> favoritesLiveData;
    private BaseExtraMoviesInfoRepository infoRepository;

    protected CompositeDisposable compositeDisposable;

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }

    public DetailMoviesViewModel(BaseExtraMoviesInfoRepository infoRepository) {
        this.infoRepository = infoRepository;
        compositeDisposable = new CompositeDisposable();
        movieLiveData = new MutableLiveData<>();
        trailersLiveData = new MutableLiveData<>();
        reviewsLiveData = new MutableLiveData<>();
        favoritesLiveData = new MutableLiveData<>();
    }

    public void onFavoriteClicked(Movie movie) {
        compositeDisposable.add(
                infoRepository.updateFavoriteMovie(movie)
                        .subscribeOn(AndroidSchedulers.mainThread())
                        .subscribe(isFavorite -> favoritesLiveData.postValue(isFavorite), Throwable::printStackTrace)
        );
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

    protected Single<Boolean> getFavoritesSingle(int id) {
        return infoRepository.getFavoriteMovieById(id)
                .subscribeOn(AndroidSchedulers.mainThread())
                .doOnSuccess(isFavorite -> favoritesLiveData.postValue(isFavorite));
    }

    public abstract void getMovieFromId(int movieId);

    public LiveData<Movie> getMovieLiveData() {
        return movieLiveData;
    }

    public LiveData<Boolean> getFavoritesLiveData() {
        return favoritesLiveData;
    }

    public LiveData<List<Review>> getReviewsLiveData() {
        return reviewsLiveData;
    }

    public LiveData<List<Trailer>> getTrailersLiveData() {
        return trailersLiveData;
    }

}
