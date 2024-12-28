package com.fawry.movies.services;

import com.fawry.movies.dao.UserDAO;
import com.fawry.movies.entity.RegularUser;
import com.fawry.movies.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegularUserServiceImpl implements  RegularUserService {

    private final UserDAO userDAO;
    private final JwtUtil jwtUtil;

    @Autowired
    public RegularUserServiceImpl(UserDAO userDao,JwtUtil jwtUtil){
        this.userDAO = userDao;
        this.jwtUtil = jwtUtil;
    }


    @Override
    public ResponseEntity<?> createUser(RegularUser theUser) {
        try {
            if (theUser.getUserName() == null || theUser.getPassword() == null) {

                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Error UserName or Password Are Missing");
            }else if (userDAO.findByUserName(theUser.getUserName()) != null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Error UserName is Already exist");
            }
            else {

                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                theUser.setPassword(passwordEncoder.encode(theUser.getPassword()));

                RegularUser saveUser = userDAO.save(theUser);
                return ResponseEntity.status(HttpStatus.CREATED).body(saveUser);
            }
        } catch (Exception exception) {
            System.out.println("Error While Saving....." + exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while saving the user.");
        }

    }

    @Override
    public RegularUser findByUserName(String userName) {
        return userDAO.findByUserName(userName);
    }

    @Override
    public ResponseEntity<?> loginUser(String userName, String password) {


        try {
            RegularUser theUser = userDAO.findByUserName(userName);
            if(theUser != null){
                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                if (passwordEncoder.matches(password, theUser.getPassword())) {
                    return ResponseEntity.status(HttpStatus.ACCEPTED).body(jwtUtil.generateToken(userName));
                }
                else{
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                            .body(" Username or Password is wrong");
                }
            }else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Error UserName is not exist");
            }


        } catch (Exception exception) {
            System.out.println("Error While Loging....." + exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while Loging the user.");
        }


    }




}
