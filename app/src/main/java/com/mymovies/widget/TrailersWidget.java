package com.mymovies.widget;

import com.mymovies.data.models.Trailer;

import java.util.List;

public class TrailersWidget {

    private List<Trailer> trailers;

    private OnTrailerClicked listener;

    public TrailersWidget(List<Trailer> trailers, OnTrailerClicked listener) {
        this.trailers = trailers;
        this.listener = listener;
    }

    public List<Trailer> getTrailers() {
        return trailers;
    }

    public OnTrailerClicked getListener() {
        return listener;
    }
}
