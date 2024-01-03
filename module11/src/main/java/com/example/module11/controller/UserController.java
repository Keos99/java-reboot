package com.example.module11.controller;

import com.example.module11.model.dto.Response;
import com.example.module11.model.entity.User;
import com.example.module11.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getUsers(){
        List<User> users = userService.getAll();
        logger.info(users.toString());
        return users;
    }

    @PostMapping("/edit/user")
    public Response editUser(@RequestBody User user){
        logger.info(user.toString());
        userService.editUser(user);
        return null;
    }
}
