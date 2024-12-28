package com.fawry.movies.rest;


import com.fawry.movies.entity.RegularUser;
import com.fawry.movies.security.JwtUtil;
import com.fawry.movies.services.RegularUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@Controller
@RequestMapping("/api/user")
public class UserRestController {
    private final RegularUserService regularUserService;



    @Autowired
    public UserRestController(RegularUserService regularUserService){
        this.regularUserService = regularUserService;

    }


    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody RegularUser regularUser){
        return regularUserService.createUser(regularUser);
    }

    @PostMapping("/login")
    public  ResponseEntity<?> loginUser(@RequestBody Map<String, String> credentials) {

        return regularUserService.loginUser(credentials.get("username"),credentials.get("password"));
    }

}
