package com.mymovies.widget;

import com.mymovies.data.models.Review;

import java.util.List;

public class ReviewsWidget {

    private List<Review> reviews;

    public ReviewsWidget(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<Review> getReviews() {
        return reviews;
    }
}
