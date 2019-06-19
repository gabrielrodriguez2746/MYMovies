package com.mymovies.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mymovies.data.models.Movie;
import com.mymovies.repositories.BaseFavoritesMoviesRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

public class FavoritesMoviesViewModel extends ViewModel {

    private BaseFavoritesMoviesRepository repository;
    private CompositeDisposable compositeDisposable;
    private MutableLiveData<List<Movie>> itemsLiveData;

    @Inject
    public FavoritesMoviesViewModel(BaseFavoritesMoviesRepository repository) {
        this.repository = repository;
        itemsLiveData = new MutableLiveData<>();
        compositeDisposable = new CompositeDisposable();
    }

    public void getFavorites() {
        compositeDisposable.add(
                repository.getFavoritesMovies()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(movies -> itemsLiveData.postValue(movies), Throwable::printStackTrace)
        );
    }

    public MutableLiveData<List<Movie>> getItemsLiveData() {
        return itemsLiveData;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }
}
