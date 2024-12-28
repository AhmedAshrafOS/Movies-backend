package com.fawry.movies.rest;


import com.fawry.movies.dto.LoginRequest;
import com.fawry.movies.entity.Movie;
import com.fawry.movies.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/api/admin")
public class AdminRestController {

    private final AuthenticationManager authenticationManager;

    private final AdminService adminService;

    @Autowired
    public AdminRestController(AdminService adminService, AuthenticationManager authenticationManager){
        this.adminService = adminService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping
    public ResponseEntity<?> addMovie(@RequestBody Movie movie){
        return  adminService.addMovie(movie);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeMovie(@PathVariable Long id){

        return  adminService.removeMovie(id);
    }

    @GetMapping
    @ResponseBody
    public List<Movie> retrieveAllMovies(){
        return adminService.retrieveAllMovies();
    }




    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );
            return ResponseEntity.ok("logined " + authentication);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }






}
