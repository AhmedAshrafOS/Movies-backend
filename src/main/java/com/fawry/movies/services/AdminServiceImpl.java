package com.fawry.movies.services;

import com.fawry.movies.dao.MovieDAO;
import com.fawry.movies.entity.Movie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {
    private static final Logger log = LoggerFactory.getLogger(AdminServiceImpl.class);
    private final MovieDAO movieDAO;


    @Autowired
    public AdminServiceImpl(MovieDAO movieDAO){
        this.movieDAO = movieDAO;
    }


    public Optional<Movie> findMovieById(String imdbID){

        return movieDAO.findByImdbID(imdbID);


    }

    @Override
    public ResponseEntity<?> addMovie(Movie movie) {
        try{

            if(movieDAO.findByImdbID(movie.getImdbID()).isPresent()){
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                        .body("This Movie is already exist");
            }
            else if(movie.hasNullValues()){
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                        .body("This Movie has broken values");
            }

            else {
                movieDAO.save(movie);
                return ResponseEntity.status(HttpStatus.ACCEPTED)
                        .body("Movie Added Successfully");
            }
        }
        catch (Exception exception){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while saving the movie.");

        }


    }

    @Override
    public ResponseEntity<?> removeMovie(Long Id) {
        try{
            if  (movieDAO.findById(Id).isPresent())  {
                movieDAO.deleteById(Id);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Movie with ID " + Id + " not found");
        }
        catch (Exception exception){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while removing the movie." + exception.getMessage());
        }

    }

    @Override
    public List<Movie> retrieveAllMovies() {
        return movieDAO.findAll();
    }
}
