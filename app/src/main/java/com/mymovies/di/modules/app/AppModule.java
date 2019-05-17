package com.mymovies.di.modules.app;

import android.content.Context;

import com.mymovies.MoviesApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Provides
    @Singleton
    public Context providesContext(MoviesApplication application) {
        return application.getApplicationContext();
    }
}
