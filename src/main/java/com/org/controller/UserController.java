package com.org.controller;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.org.exceptions.RecordAlreadyPresentException;
import com.org.exceptions.RecordNotFoundException;
import com.org.model.User;
import com.org.service.UserService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@ComponentScan(basePackages = "com")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/createUser")
    @ExceptionHandler(RecordAlreadyPresentException.class)
    public void addUser(@RequestBody User newUser) {
        System.out.println("name: " + newUser.getName());
        userService.createUser(newUser);
    }


    @GetMapping("/readAllUsers")
    public Iterable<User> readAllUsers() {

        return userService.displayAllUser();
    }

    //@PutMapping("/updateUser")
    @ExceptionHandler(RecordNotFoundException.class)
    public void updateUser(@RequestBody User updateUser) {

        userService.updateUser(updateUser);
    }

    //@GetMapping("/searchUser/{id}")
    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<?> searchUserByID(@PathVariable("id") Long userId) {

        return userService.findUserById(userId);
    }

    //@DeleteMapping("/deleteUser/{id}")
    @ExceptionHandler(RecordNotFoundException.class)
    public void deleteBookingByID(@PathVariable("id") Long userId) {

        userService.deleteUser(userId);
    }
}
