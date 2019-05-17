package com.mymovies.di.modules.main;

import com.mymovies.di.modules.network.ApiModule;
import com.mymovies.di.scopes.ActivityScope;
import com.mymovies.repositories.BasePopularMoviesRepository;
import com.mymovies.repositories.PopularMoviesRepository;

import dagger.Binds;
import dagger.Module;

@Module(includes = {ApiModule.class})
public abstract class MoviesRepositoryModule {

    @Binds
    @ActivityScope
    abstract BasePopularMoviesRepository bindMoviesRepository(PopularMoviesRepository repository);
}
