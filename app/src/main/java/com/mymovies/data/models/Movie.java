package com.mymovies.data.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "movie")
public class Movie {

    @PrimaryKey
    private int id;

    @ColumnInfo(name = "original_title")
    private String originalTitle;

    private String title;

    private Double popularity;

    @ColumnInfo(name = "backdrop_path")
    private String backdropPath;

    @ColumnInfo(name = "poster_path")
    private String posterPath;

    @ColumnInfo(name = "release_date")
    private String releaseDate;

    @ColumnInfo(name = "overview")
    private String overview;

    public Movie(int id, String originalTitle, String title, Double popularity, String backdropPath, String posterPath, String releaseDate, String overview) {
        this.id = id;
        this.originalTitle = originalTitle;
        this.title = title;
        this.popularity = popularity;
        this.backdropPath = backdropPath;
        this.posterPath = posterPath;
        this.releaseDate = releaseDate;
        this.overview = overview;
    }

    public int getId() {
        return id;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getTitle() {
        return title;
    }

    public Double getPopularity() {
        return popularity;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getOverview() {
        return overview;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return id == movie.id &&
                Objects.equals(originalTitle, movie.originalTitle) &&
                Objects.equals(title, movie.title) &&
                Objects.equals(popularity, movie.popularity) &&
                Objects.equals(backdropPath, movie.backdropPath) &&
                Objects.equals(posterPath, movie.posterPath) &&
                Objects.equals(releaseDate, movie.releaseDate) &&
                Objects.equals(overview, movie.overview);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, originalTitle, title, popularity, backdropPath, posterPath, releaseDate, overview);
    }
}
