package com.fawry.movies.services;

import com.fawry.movies.entity.Movie;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface AdminService {

    ResponseEntity<?> addMovie(Movie movie);

    ResponseEntity<?> removeMovie(Long Id);

    List<Movie> retrieveAllMovies();

    Optional<Movie> findMovieById(String imdbID);



}
