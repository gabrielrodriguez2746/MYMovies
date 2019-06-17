package com.mymovies.di.modules.main.repositories;

import com.mymovies.di.scopes.FragmentScope;
import com.mymovies.repositories.BaseExtraMoviesInfoRepository;
import com.mymovies.repositories.ExtraMoviesInfoRepository;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class MoviesInfoRepositoryModule {

    @Binds
    @FragmentScope
    public abstract BaseExtraMoviesInfoRepository bindExtraMoviesInfoRepository(ExtraMoviesInfoRepository repository);
}
