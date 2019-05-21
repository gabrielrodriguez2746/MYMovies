package com.mymovies.di.modules.main.repositories;

import com.mymovies.di.modules.network.ApiModule;
import com.mymovies.di.scopes.ActivityScope;
import com.mymovies.repositories.BasePopularMoviesRepository;
import com.mymovies.repositories.BaseTopRatedMoviesRepository;
import com.mymovies.repositories.PopularMoviesRepository;
import com.mymovies.repositories.TopRatedMoviesRepository;

import dagger.Binds;
import dagger.Module;

@Module(includes = {ApiModule.class})
public abstract class MoviesRepositoryModule {

    @Binds
    @ActivityScope
    public abstract BasePopularMoviesRepository bindPopularMoviesRepository(PopularMoviesRepository repository);

    @Binds
    @ActivityScope
    public abstract BaseTopRatedMoviesRepository bindTopRatedMoviesRepository(TopRatedMoviesRepository repository);
}
