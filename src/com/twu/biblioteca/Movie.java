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

    public boolean isNameEqual(String nameToCompare){
        return (this.name).equals(nameToCompare);
    }

    public boolean isYearEqual(int yearToCompare){
        return this.year == yearToCompare;
    }

    public boolean isDirectorEqual(String directorToCompare){
        return (this.director).equals(directorToCompare);
    }

    public boolean isRatingEqual(int ratingToCompare){
        return this.rating == ratingToCompare;
    }

    public boolean areMovieDetailsEqual(Movie movieCompared){
        return isNameEqual(movieCompared.name) && isYearEqual(movieCompared.year)
                && isDirectorEqual(movieCompared.director) && isRatingEqual(movieCompared.rating);
    }

    @Override
    public boolean equals(Object object){
        if(!(object instanceof Movie) || object == null){
            return false;
        }

        Movie movieCompared = (Movie) object;

        return areMovieDetailsEqual(movieCompared);
    }

    public static String convertRatingToString(int rating){
        if(rating == 0){
            return "unrated";
        }

        return rating + " stars";
    }


    @Override
    public String printDetails() {
       return this.name + " (" + this.year + "), " + this.director + ", " + convertRatingToString(this.rating);
    }
}
