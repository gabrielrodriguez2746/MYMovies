package com.mymovies.di.modules.main.favorites;

import androidx.lifecycle.ViewModel;

import com.mymovies.di.keys.ViewModelKey;
import com.mymovies.viewmodels.FavoritesMoviesViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class FavoritesMoviesViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(FavoritesMoviesViewModel.class)
    public abstract ViewModel bindPouplarMoviesViewModel(FavoritesMoviesViewModel viewModel);
}
