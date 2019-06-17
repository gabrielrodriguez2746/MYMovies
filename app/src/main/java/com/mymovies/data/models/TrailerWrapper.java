package com.mymovies.data.models;

import java.util.List;

public class TrailerWrapper {

    private List<Trailer> trailers;

    public TrailerWrapper(List<Trailer> trailers) {
        this.trailers = trailers;
    }

    public void setTrailers(List<Trailer> trailers) {
        this.trailers = trailers;
    }

    public List<Trailer> getTrailers() {
        return trailers;
    }
}
