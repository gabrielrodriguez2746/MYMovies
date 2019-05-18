package com.mymovies.di.modules.network;

import com.mymovies.rest.MoviesService;

import dagger.Module;
import dagger.Provides;
import dagger.Reusable;
import retrofit2.Retrofit;

@Module
public class ApiModule {

    @Provides
    @Reusable
    MoviesService provideMoviesService(Retrofit retrofit) {
        return retrofit.create(MoviesService.class);
    }
}
