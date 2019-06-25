package com.mymovies.di.modules.main.detail;

import androidx.lifecycle.ViewModel;

import com.mymovies.di.keys.ViewModelKey;
import com.mymovies.viewmodels.detail.FavoritesMoviesDetailViewModel;
import com.mymovies.viewmodels.detail.PopularMoviesDetailViewModel;
import com.mymovies.viewmodels.detail.TopRatedMoviesDetailViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class PopularMoviesDetailViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(PopularMoviesDetailViewModel.class)
    public abstract ViewModel bindPopularMoviesDetailViewModel(PopularMoviesDetailViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(TopRatedMoviesDetailViewModel.class)
    public abstract ViewModel bindTopRatedMoviesDetailViewModel(TopRatedMoviesDetailViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(FavoritesMoviesDetailViewModel.class)
    public abstract ViewModel bindFavoritesMoviesDetailViewModel(FavoritesMoviesDetailViewModel viewModel);
}
