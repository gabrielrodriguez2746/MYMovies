package com.mymovies.viewmodels;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

import com.mymovies.data.models.Movie;
import com.mymovies.repositories.BaseTopRatedMoviesRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

public class TopRatedMoviesViewModel extends ViewModel {

    private BaseTopRatedMoviesRepository repository;
    private CompositeDisposable compositeDisposable;
    private MoviesFactory dataFactory;
    private LiveData<PagedList<Movie>> itemsLiveData;

    @Inject
    public TopRatedMoviesViewModel(BaseTopRatedMoviesRepository repository) {
        this.repository = repository;
        compositeDisposable = new CompositeDisposable();
        dataFactory = new MoviesFactory(new DataController()); // Gabriel This should be also injected
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }

    public LiveData<PagedList<Movie>> getItemsLiveData() {
        if (itemsLiveData == null) {
            itemsLiveData = new LivePagedListBuilder<>(dataFactory,
                    new PagedList.Config.Builder()
                            .setEnablePlaceholders(true)
                            .setInitialLoadSizeHint(30)
                            .setPageSize(20)
                            .build()).build();
        }
        return itemsLiveData;
    }


    // Gabriel This should not be inner
    class DataController extends PageKeyedDataSource<Integer, Movie> {

        @Override
        public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Movie> callback) {
            compositeDisposable.add(
                    repository.getTopRatedMoviesByPage(1)
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(
                                    (result) -> processData(callback, result),
                                    Throwable::printStackTrace) // Gabriel Notify
            );
        }

        private void processData(@NonNull LoadInitialCallback<Integer, Movie> callback, List<Movie> result) {
            callback.onResult(result, null, result.isEmpty() ? null : 2);
        }

        @Override
        public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Movie> callback) {
            // Gabriel Notify to show loading
        }

        @Override
        public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Movie> callback) {
            compositeDisposable.add(
                    repository.getTopRatedMoviesByPage(params.key)
                            .observeOn(AndroidSchedulers.mainThread()).subscribe((result) ->
                                    callback.onResult(result, result.isEmpty() ? null : params.key + 1),
                            (error) -> {
                                // Gabriel Notify
                            })
            );
        }
    }

    class MoviesFactory extends DataSource.Factory<Integer, Movie> {

        private DataController controller;

        MoviesFactory(DataController controller) {
            this.controller = controller;
        }

        @NonNull
        @Override
        public DataSource<Integer, Movie> create() {
            return controller;
        }
    }
}
