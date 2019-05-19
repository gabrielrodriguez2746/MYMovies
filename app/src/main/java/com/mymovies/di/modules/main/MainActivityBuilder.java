package com.mymovies.di.modules.main;

import com.mymovies.activities.MainActivity;
import com.mymovies.di.modules.main.popular.DetailMovieFragmentBuilder;
import com.mymovies.di.modules.main.popular.PopularMoviesFragmentBuilder;
import com.mymovies.di.scopes.ActivityScope;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainActivityBuilder {

    @ContributesAndroidInjector(modules = {
            PopularMoviesFragmentBuilder.class,
            DetailMovieFragmentBuilder.class,
            MoviesRepositoryModule.class
    })
    @ActivityScope
    abstract MainActivity bindMainActivity();

}
