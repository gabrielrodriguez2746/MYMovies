package com.mymovies.di.modules.main.popular;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.mymovies.fragments.PopularMoviesListFragment;
import com.mymovies.viewmodels.PopularMoviesViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public class PopularMoviesViewModelProvider {

    @Provides
    public PopularMoviesViewModel providePopularMoviesViewModel(
            PopularMoviesListFragment fragment, ViewModelProvider.Factory factory) {
        return ViewModelProviders.of(fragment, factory).get(PopularMoviesViewModel.class);
    }
}
