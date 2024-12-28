package com.fawry.movies.services;


import com.fawry.movies.dao.RatingDAO;
import com.fawry.movies.entity.Movie;
import com.fawry.movies.entity.Rating;
import com.fawry.movies.entity.RegularUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RatingServiceImpl implements RatingService{

    private final RatingDAO ratingDAO;

    @Autowired
    public RatingServiceImpl(RatingDAO ratingDAO){

        this.ratingDAO = ratingDAO;
    }


    @Override
    @Transactional
    public ResponseEntity<?> addRating(Movie movie, RegularUser user, int rating) {

        try{
            if(movie == null || user == null || rating> 5){
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("There is Something wrong ");
            }

            Rating ratingTest= ratingDAO.findByMovieAndUser(movie, user);

             if (ratingTest !=null) {
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("you already rated this movie");
            }
            else {
                ratingDAO.save(new Rating(user,movie,rating));
                return ResponseEntity.status(HttpStatus.ACCEPTED).body("Rated Successfully");
            }

        }
        catch (Exception exception){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Error while saving...");
        }


    }

    @Override
    public ResponseEntity<?> removeRating(Movie movie, RegularUser user) {
        try{
            if(movie == null || user == null){
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("There is Something wrong ");
            }
            Rating theCurrentRating = ratingDAO.findByMovieAndUser(movie, user);

            if (theCurrentRating != null ) {
                 ratingDAO.delete(ratingDAO.findByMovieAndUser(movie, user));
                 return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Deleted Successfully ");
            }else{
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("there is no such Rating  ");
            }
        }
        catch (Exception exception){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Error while Deleting...");
        }
    }

    @Override
    public ResponseEntity<?> updateRating(Movie movie, RegularUser user ,int rating) {
        try{
            if(movie == null || user == null){
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("There is Something wrong ");
            }
            Rating theCurrentRating = ratingDAO.findByMovieAndUser(movie, user);

            if (theCurrentRating != null ) {
                theCurrentRating.setRating(rating);
                ratingDAO.save(theCurrentRating);
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Updated Successfully ");
            }else{
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("there is no such Rating  ");
            }
        }
        catch (Exception exception){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Error while Updating...");
        }

    }

    @Override
    public int retrieveRating(Movie movie, RegularUser user) {
        try {
            if (movie == null || user == null) {
                return 0;
            }
            Rating theCurrentRating = ratingDAO.findByMovieAndUser(movie, user);

            if (theCurrentRating != null ){
                return theCurrentRating.getRating();
            }
           return (int)0;
        }
        catch (Exception e){
            return  0;
        }
    }
}
