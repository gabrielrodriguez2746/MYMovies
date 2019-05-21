package com.mymovies.di.modules.main.popular;

import com.mymovies.di.scopes.FragmentScope;
import com.mymovies.fragments.DetailMovieFragment;
import com.mymovies.fragments.PopularMoviesListFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class DetailMovieFragmentBuilder {

    @ContributesAndroidInjector(modules = {PopularMoviesDetailViewModelModule.class})
    @FragmentScope
    public abstract DetailMovieFragment bindDetailMovieFragment();

}
