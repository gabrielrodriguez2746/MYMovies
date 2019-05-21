package com.mymovies.di.modules.main.topRated;

import androidx.lifecycle.ViewModel;

import com.mymovies.di.keys.ViewModelKey;
import com.mymovies.viewmodels.TopRatedMoviesViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class TopRatedMoviesViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(TopRatedMoviesViewModel.class)
    public abstract ViewModel bindTopRatedMoviesViewModel(TopRatedMoviesViewModel viewModel);
}
