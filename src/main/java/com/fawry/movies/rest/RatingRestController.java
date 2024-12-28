package com.fawry.movies.rest;


import com.fawry.movies.dto.RatingRequest;
import com.fawry.movies.entity.Movie;
import com.fawry.movies.entity.Rating;
import com.fawry.movies.entity.RegularUser;
import com.fawry.movies.services.AdminService;
import com.fawry.movies.services.RatingService;
import com.fawry.movies.services.RegularUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/api/rating")
public class RatingRestController {

    private final RatingService ratingService;
    private final RegularUserService regularUserService;
    private final AdminService adminService;

    public RatingRestController(RatingService ratingService, RegularUserService regularUserService,AdminService adminService ){
        this.ratingService = ratingService;
        this.regularUserService = regularUserService;
        this.adminService = adminService;
    }

    @PostMapping
    public ResponseEntity<?> addRating(@RequestBody  RatingRequest ratingRequest) {
        try {

            Optional<Movie> movieOptional = adminService.findMovieById(ratingRequest.getMovieId());
            RegularUser user = regularUserService.findByUserName(ratingRequest.getUserName());

            Movie movie = null;

            if (movieOptional.isPresent()) {
                 movie = movieOptional.get();
            } else {
                throw new IllegalAccessException("Movie with ID " + ratingRequest.getMovieId()+ " not found");
            }

            if (user == null) {
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Invalid user");
            }

            return ratingService.addRating(movie, user,ratingRequest.getRating() );

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error while saving the rating: " + e.getMessage());
        }
    }

    @PostMapping("/get")
    public ResponseEntity<?>  retrieveRating(@RequestBody RatingRequest ratingRequest){
        try {

            Optional<Movie> movieOptional = adminService.findMovieById(ratingRequest.getMovieId());
            RegularUser user = regularUserService.findByUserName(ratingRequest.getUserName());
            Movie movie = null;

            if (user == null) {
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Invalid user");
            }

            if (movieOptional.isPresent()) {
                movie = movieOptional.get();
            }
            else {
                throw new IllegalAccessException("Rating Movie with ID " + ratingRequest.getMovieId()+ " not found");
            }


            return ResponseEntity.status(HttpStatus.ACCEPTED).body(ratingService.retrieveRating(movie,user));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error while saving the rating: " + e.getMessage());

        }
    }

    @DeleteMapping
    public ResponseEntity<?> removeRating(@RequestBody  RatingRequest ratingRequest) {

        try {
            Optional<Movie> movieOptional = adminService.findMovieById(ratingRequest.getMovieId());
            RegularUser user = regularUserService.findByUserName(ratingRequest.getUserName());

            Movie movie = null;

            if (movieOptional.isPresent()) {
                movie = movieOptional.get();
            } else {
                throw new IllegalAccessException("Movie with ID " + ratingRequest.getMovieId() + " not found");
            }

            if (user == null) {
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Invalid user");
            }

            ratingService.removeRating(movie,user);

            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Removed Successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error while saving the rating: " + e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> updateRating(@RequestBody  RatingRequest ratingRequest){
        try {

            Optional<Movie> movieOptional = adminService.findMovieById(ratingRequest.getMovieId());
            RegularUser user = regularUserService.findByUserName(ratingRequest.getUserName());

            Movie movie = null;

            if (movieOptional.isPresent()) {
                movie = movieOptional.get();
            } else {
                throw new IllegalAccessException("Movie with ID " + ratingRequest.getMovieId() + " not found");
            }

            if (user == null) {
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Invalid user");
            }

            ratingService.updateRating(movie, user, ratingRequest.getRating());

            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Rated Successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error while saving the rating: " + e.getMessage());
        }
    }

}
