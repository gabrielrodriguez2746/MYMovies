package com.mymovies.data.models;

import java.util.List;

public class ReviewWrapper {

    private List<Review> reviews;

    public ReviewWrapper(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}
