package com.akuna.security.controller;


import com.akuna.security.domain.User;
import com.akuna.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController
{
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public User getUser(String id) {
        return userService.get(id);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public void createUser(@Valid @RequestBody User user) {
        userService.create(user);
    }
}
