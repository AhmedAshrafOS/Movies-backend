package com.fawry.movies.dao;

import com.fawry.movies.entity.RegularUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO  extends JpaRepository<RegularUser, Long> {
    RegularUser findByUserName(String userName);
}
