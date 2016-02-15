package com.rcoedo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rx.Observable;

import com.rcoedo.model.domain.User;
import com.rcoedo.model.services.UserService;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/users")
    public Observable<List<User>> getUsers() {
        return userService.getUsers().toList();
    }

    @RequestMapping("/")
    public String home() {
        return "No place like home";
    }

}
