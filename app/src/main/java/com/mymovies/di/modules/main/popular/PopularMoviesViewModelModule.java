package com.mymovies.di.modules.main.popular;

import androidx.lifecycle.ViewModel;

import com.mymovies.di.keys.ViewModelKey;
import com.mymovies.viewmodels.main.PopularMoviesViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class PopularMoviesViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(PopularMoviesViewModel.class)
    public abstract ViewModel bindPouplarMoviesViewModel(PopularMoviesViewModel viewModel);
}
