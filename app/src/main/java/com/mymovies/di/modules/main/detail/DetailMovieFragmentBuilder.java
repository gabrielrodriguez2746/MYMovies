package com.mymovies.di.modules.main.detail;

import com.mymovies.di.modules.main.repositories.MoviesInfoRepositoryModule;
import com.mymovies.di.scopes.FragmentScope;
import com.mymovies.fragments.DetailMovieFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class DetailMovieFragmentBuilder {

    @ContributesAndroidInjector(modules = {
            MoviesInfoRepositoryModule.class,
            PopularMoviesDetailViewModelModule.class
    })
    @FragmentScope
    public abstract DetailMovieFragment bindDetailMovieFragment();

}
