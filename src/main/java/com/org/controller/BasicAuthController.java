package com.org.controller;

import com.org.exceptions.RecordAlreadyPresentException;
import com.org.model.User;
import com.org.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@CrossOrigin(origins = "*",allowedHeaders = "*")
@ComponentScan(basePackages = "com")
@RestController
@RequestMapping("/login")
public class BasicAuthController {

    @Autowired
    UserService userService;

    @RequestMapping("/")
    public String defaultHome() {
        System.out.println("Welcome");
        return "home";
    }

    @GetMapping("/auth")
    @ExceptionHandler(RecordAlreadyPresentException.class)
    public void login(@RequestHeader String newUser,@RequestHeader String password) {
        userService.findByUsername(newUser,password);
        System.out.println("login");
    }
}
