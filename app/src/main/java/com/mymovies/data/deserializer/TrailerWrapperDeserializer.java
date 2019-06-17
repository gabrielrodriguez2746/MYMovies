package com.mymovies.data.deserializer;

import androidx.annotation.Nullable;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.mymovies.data.models.Review;
import com.mymovies.data.models.Trailer;
import com.mymovies.data.models.TrailerWrapper;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static com.mymovies.helpers.Helpers.getGenericOrDefault;

public class TrailerWrapperDeserializer implements JsonDeserializer<TrailerWrapper> {

    @Override
    public TrailerWrapper deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonArray trailers = getGenericOrDefault(json.getAsJsonObject(), "results", new JsonArray());
        ArrayList<Trailer> mappedTrailers = new ArrayList<>();
        for (JsonElement trailer : trailers) {
            Trailer mappedTrailer = mapTrailerFromElement(trailer.getAsJsonObject());
            if (mappedTrailer != null) {
                mappedTrailers.add(mappedTrailer);
            }
        }
        return new TrailerWrapper(mappedTrailers);
    }

    @Nullable
    private static Trailer mapTrailerFromElement(JsonObject reviewObject) {
        String id = getGenericOrDefault(reviewObject, "id", "");
        if (id.isEmpty()) {
            return null;
        } else {
            String name = getGenericOrDefault(reviewObject, "name", "");
            return new Trailer(id, name);
        }
    }
}
