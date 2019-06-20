package com.mymovies.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.mymovies.data.models.Movie;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Observable;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public abstract class FavoritesMoviesDao {

    @Insert(onConflict = REPLACE)
    public abstract Completable insert(Movie movie);

    @Query("SELECT * FROM movie WHERE id = :movieId")
    public abstract Maybe<Movie> getMovieById(int movieId);

    @Query("SELECT * FROM movie")
    public abstract Observable<List<Movie>> getFavoritesMovies();

    @Query("DELETE FROM movie WHERE id = :movieId")
    public abstract Completable deleteMovieById(int movieId);

}
