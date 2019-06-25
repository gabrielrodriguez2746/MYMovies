package com.mymovies.di.modules.main;

import com.mymovies.activities.MainActivity;
import com.mymovies.di.modules.main.detail.DetailMovieFragmentBuilder;
import com.mymovies.di.modules.main.favorites.FavoritesMoviesFragmentBuilder;
import com.mymovies.di.modules.main.popular.MoviesListFragmentBuilder;
import com.mymovies.di.scopes.ActivityScope;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainActivityBuilder {

    @ContributesAndroidInjector(modules = {
            MoviesListFragmentBuilder.class,
            FavoritesMoviesFragmentBuilder.class,
            DetailMovieFragmentBuilder.class
    })
    @ActivityScope
    abstract MainActivity bindMainActivity();

}
