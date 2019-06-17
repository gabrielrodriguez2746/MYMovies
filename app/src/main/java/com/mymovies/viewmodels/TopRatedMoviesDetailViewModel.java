package com.mymovies.viewmodels;

import com.mymovies.repositories.BaseExtraMoviesInfoRepository;
import com.mymovies.repositories.BaseTopRatedMoviesRepository;

import javax.inject.Inject;

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
                repository.getSingleMovieFromId(movieId)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                (result) -> movieLiveData.postValue(result), // TODO This should be mapped here
                                Throwable::printStackTrace) // TODO Notify
        );
    }
}
