package com.mymovies.data.deserializer;

import androidx.annotation.Nullable;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.mymovies.data.models.Review;
import com.mymovies.data.models.ReviewWrapper;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static com.mymovies.helpers.Helpers.getGenericOrDefault;

public class ReviewWrapperDeserializer implements JsonDeserializer<ReviewWrapper> {

    @Override
    public ReviewWrapper deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonArray reviews = getGenericOrDefault(json.getAsJsonObject(), "results", new JsonArray());
        ArrayList<Review> mappedReviews = new ArrayList<>();
        for (JsonElement review : reviews) {
            Review mappedReview = mapReviewFromElement(review.getAsJsonObject());
            if (mappedReview != null) {
                mappedReviews.add(mappedReview);
            }
        }
        return new ReviewWrapper(mappedReviews);
    }

    @Nullable
    private static Review mapReviewFromElement(JsonObject reviewObject) {
        String author = getGenericOrDefault(reviewObject, "author", "");
        String content = getGenericOrDefault(reviewObject, "content", "");
        if (author.isEmpty() || content.isEmpty()) {
            return null;
        } else {
            return new Review(author, content);
        }
    }
}
