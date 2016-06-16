package com.twu.biblioteca;

/**
 * Created by droman on 6/16/16.
 */
public class Movie implements Item{
    private String name;
    private int year;
    private String director;
    private int rating;

    public Movie(String name, int year, String director, int rating) {
        this.name = name;
        this.year = year;
        this.director = director;
        this.rating = rating;
    }

    public int getRating() {
        return rating;
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public String getDirector() {
        return director;
    }

    @Override
    public boolean equals(Object object){
        return false;
    }

    @Override
    public String printDetails() {
        return null;
    }
}
