package com.fawry.movies.dao;


import com.fawry.movies.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieDAO extends JpaRepository<Movie, Long> {

    Optional<Movie> findByImdbID(String imdbID);

}
