package com.mymovies.di.modules.main.topRated;

import com.mymovies.di.scopes.FragmentScope;
import com.mymovies.fragments.TopRatedMoviesListFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class TopRatedMoviesFragmentBuilder {

    @ContributesAndroidInjector(modules = {TopRatedMoviesViewModelModule.class})
    @FragmentScope
    public abstract TopRatedMoviesListFragment bindTopRatedMoviesFragment();

}
