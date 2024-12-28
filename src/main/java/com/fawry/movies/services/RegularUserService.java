package com.fawry.movies.services;

import com.fawry.movies.entity.RegularUser;
import org.springframework.http.ResponseEntity;

public interface RegularUserService {


    ResponseEntity<?> createUser(RegularUser theUser);

    RegularUser findByUserName(String userName);

    ResponseEntity<?> loginUser(String userName , String password);


}
