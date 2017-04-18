package entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by icons on 9.4.17.
 */
public class Movie {

    private long id;
    private String name;
    private int year;
    private String country;
    private String genre;
    private Set<Actor> setActor = new HashSet<>();
    //private Set<Actor> setProducer = new HashSet<>();
    private Set<Review> setReviews = new HashSet<>();

    public Movie(long id, String name,Set<Review> setReviews) {
        this.id = id;
        this.name = name;
        this.setReviews = setReviews;
    }

    public Movie(long id, String name, int year, String country, String genre) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.country = country;
        this.genre = genre;
    }

    public Movie(String name, int year, String country, String genre) {
        this.name = name;
        this.year = year;
        this.country = country;
        this.genre = genre;
    }

    public Movie(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Movie(long id) {
        this.id = id;
    }

    public Movie(String name) {
        this.name = name;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Set<Actor> getSetActor() {
        return setActor;
    }

    public void addSetActor(Actor actor) {
        setActor.add(actor);
    }


    public Set<Review> getSetReviews() {
        return setReviews;
    }

    public void addSetReview(Review review) {
        setReviews.add(review);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", year=" + year +
                ", country='" + country + '\'' +
                ", genre='" + genre + '\'' +
                ", setActor=" + setActor +
                ", setReviews=" + setReviews +
                '}';
    }

}