package com.mymovies.di.modules.main.popular;

import com.mymovies.di.modules.main.topRated.TopRatedMoviesViewModelModule;
import com.mymovies.di.scopes.FragmentScope;
import com.mymovies.fragments.MoviesListFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MoviesListFragmentBuilder {

    @ContributesAndroidInjector(modules = {
            PopularMoviesViewModelModule.class,
            TopRatedMoviesViewModelModule.class
    })
    @FragmentScope
    public abstract MoviesListFragment bindTopMoviesFragment();

}
