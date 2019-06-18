package com.mymovies.di.modules.main.repositories;

import com.mymovies.repositories.BasePopularMoviesRepository;
import com.mymovies.repositories.BaseTopRatedMoviesRepository;
import com.mymovies.repositories.PopularMoviesRepository;
import com.mymovies.repositories.TopRatedMoviesRepository;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class MoviesRepositoryModule {

    @Binds
    @Singleton
    public abstract BasePopularMoviesRepository bindPopularMoviesRepository(PopularMoviesRepository repository);

    @Binds
    @Singleton
    public abstract BaseTopRatedMoviesRepository bindTopRatedMoviesRepository(TopRatedMoviesRepository repository);

}
