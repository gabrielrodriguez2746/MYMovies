package com.mymovies.di.modules.main.popular;

import com.mymovies.di.scopes.FragmentScope;
import com.mymovies.fragments.PopularMoviesListFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class PopularMoviesFragmentBuilder {

    @ContributesAndroidInjector(modules = {PopularMoviesViewModelModule.class})
    @FragmentScope
    public abstract PopularMoviesListFragment bindTopMoviesFragment();

}
