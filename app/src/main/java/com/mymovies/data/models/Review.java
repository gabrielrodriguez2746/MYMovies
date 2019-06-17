package com.mymovies.data.models;

public class Review {

    public Review(String author, String content) {
        this.author = author;
        this.content = content;
    }

    private String author;

    private String content;

    @Override
    public String toString() {
        return "Review{" +
                "author='" + author + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
