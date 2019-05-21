package com.mymovies.di.modules.main.detail;

import androidx.lifecycle.ViewModel;

import com.mymovies.di.keys.ViewModelKey;
import com.mymovies.viewmodels.PopularMoviesDetailViewModel;
import com.mymovies.viewmodels.TopRatedMoviesDetailViewModel;

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
}
