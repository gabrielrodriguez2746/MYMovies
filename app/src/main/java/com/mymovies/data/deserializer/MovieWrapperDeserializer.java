package com.mymovies.data.deserializer;

import android.net.Uri;

import androidx.annotation.Nullable;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.internal.LazilyParsedNumber;
import com.mymovies.data.models.Movie;
import com.mymovies.data.models.MovieWrapper;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static com.mymovies.helpers.Helpers.getGenericOrDefault;

public class MovieWrapperDeserializer implements JsonDeserializer<MovieWrapper> {

    private static Uri BASE_POSTER_URI = Uri.parse("https://image.tmdb.org/t/p/w185/");
    private static Uri BASE_BACKDROP_URI = Uri.parse("https://image.tmdb.org/t/p/w780/");

    @Override
    public MovieWrapper deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonArray movies = getGenericOrDefault(json.getAsJsonObject(), "results", new JsonArray());
        ArrayList<Movie> mappedMovies = new ArrayList<>();
        for (JsonElement movie : movies) {
            Movie mappedMovie = mapCarItemFromElement(movie.getAsJsonObject());
            if (mappedMovie != null) {
                mappedMovies.add(mappedMovie);
            }
        }
        return new MovieWrapper(mappedMovies);
    }

    @Nullable
    private static Movie mapCarItemFromElement(JsonObject carObject) {
        int id = getGenericOrDefault(carObject, "id", new LazilyParsedNumber("-1")).intValue();
        if (id == -1) {
            return null;
        } else {
            String title = getGenericOrDefault(carObject, "title", "");
            String originalTitle = getGenericOrDefault(carObject, "original_title", "");

            Double voteAverage = getGenericOrDefault(carObject, "vote_average",
                    new LazilyParsedNumber("0.0")).doubleValue();

            String partialPosterPath = getGenericOrDefault(carObject, "poster_path", "");
            String partialBackdropPath = getGenericOrDefault(carObject, "backdrop_path", "");
            String posterPath = BASE_POSTER_URI.buildUpon().appendEncodedPath(partialPosterPath).build().toString();
            String backdropPath = BASE_BACKDROP_URI.buildUpon().appendEncodedPath(partialBackdropPath).build().toString();

            String overview = getGenericOrDefault(carObject, "overview", "");
            String releaseDate = getGenericOrDefault(carObject, "release_date", "");

            return new Movie(id, originalTitle, title, voteAverage, backdropPath, posterPath, releaseDate, overview);
        }
    }
}
