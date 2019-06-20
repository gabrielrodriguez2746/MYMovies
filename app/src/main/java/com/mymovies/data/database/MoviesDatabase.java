package com.mymovies.data.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.mymovies.data.dao.FavoritesMoviesDao;
import com.mymovies.data.models.Movie;

@Database(entities = {Movie.class}, version = 1)
public abstract class MoviesDatabase extends RoomDatabase {

    public abstract FavoritesMoviesDao favoritesMoviesDao();
}
