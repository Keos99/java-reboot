package com.example.module11.controller;

import com.example.module11.model.dto.Response;
import com.example.module11.model.entity.User;
import com.example.module11.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

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
        try {
            userService.editUser(user);
        } catch (NoSuchElementException e){
            return new Response(false, e.getMessage());
        }
        return new Response(true,"Пользователь отредактирован");
    }

    @PostMapping("/add/user")
    public Response addUser(@RequestBody User user){
        logger.info(user.toString());
        try {
            userService.addUser(user);
        } catch (IllegalArgumentException e){
            return new Response(false, e.getMessage());
        }
        return new Response(true,"Пользователь добавлен");
    }

    @PostMapping("/delete/user")
    public Response deleteUser(@RequestBody User user){
        logger.info(user.toString());
        try {
            userService.deleteUser(user);
        } catch (NoSuchElementException e){
            return new Response(false, e.getMessage());
        }
        return new Response(true,"Пользователь удален");
    }
}
