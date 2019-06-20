package com.mymovies.di.modules.app;

import android.content.Context;

import androidx.room.Room;

import com.mymovies.data.dao.FavoritesMoviesDao;
import com.mymovies.data.database.MoviesDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppDataBaseModule {

    @Provides
    @Singleton
    public MoviesDatabase provideFavoritesMoviesDataBase(Context context) {
        return Room.databaseBuilder(context, MoviesDatabase.class, "Movies.db")
                .fallbackToDestructiveMigration().build();
    }

    // TODO Improve also this, should not be singleton
    @Provides
    @Singleton
    public FavoritesMoviesDao provideFavoritesMoviesDao(MoviesDatabase database) {
        return database.favoritesMoviesDao();
    }

}
