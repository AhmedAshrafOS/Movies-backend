package com.fawry.movies.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.*;

@Entity
@Table(name = "ratings")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "fk_ratings_regular_users"))
    private RegularUser user;


    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false, foreignKey = @ForeignKey(name = "fk_ratings_movies"))
    private Movie movie;

    @Column(nullable = false)
    private int rating;


    public Rating() {}

    public Rating(RegularUser user, Movie movie, int rating) {
        this.user = user;
        this.movie = movie;
        this.rating = rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getRating() {
        return this.rating ;
    }

}

