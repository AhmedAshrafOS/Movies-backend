package com.fawry.movies.dao;


import com.fawry.movies.entity.Movie;
import com.fawry.movies.entity.Rating;
import com.fawry.movies.entity.RegularUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RatingDAO  extends JpaRepository<Rating, Long> {

    Rating findByMovieAndUser(Movie movie, RegularUser user);

}
