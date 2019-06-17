package com.mymovies.data.models;

public class Trailer {

    public Trailer(String id, String name) {
        this.id = id;
        this.name = name;
    }

    private String id;

    private String name;

    @Override
    public String toString() {
        return "Trailer{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
