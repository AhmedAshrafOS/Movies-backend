package com.fawry.movies.services;

import com.fawry.movies.entity.Movie;
import com.fawry.movies.entity.RegularUser;
import org.springframework.http.ResponseEntity;

public interface RatingService {

    ResponseEntity<?> addRating(Movie movie, RegularUser user, int rating);

    ResponseEntity<?> removeRating(Movie movie, RegularUser user);

    ResponseEntity<?> updateRating(Movie movie, RegularUser user ,int rating);

    int retrieveRating(Movie movie, RegularUser user);


}
