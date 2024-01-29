package ru.sberbank.edu.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.sberbank.edu.model.dto.Response;
import ru.sberbank.edu.model.entity.UserInfo;
import ru.sberbank.edu.service.UserService;


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
    public List<UserInfo> getUsers(){
        List<UserInfo> userInfos = userService.getAll();
        logger.info(userInfos.toString());
        return userInfos;
    }

    @PostMapping("/edit/user")
    public Response editUser(@RequestBody UserInfo userInfo){
        logger.info(userInfo.toString());
        try {
            userService.editUser(userInfo);
        } catch (NoSuchElementException e){
            return new Response(false, e.getMessage());
        }
        return new Response(true,"Пользователь отредактирован");
    }

    @PostMapping("/add/user")
    public Response addUser(@RequestBody UserInfo userInfo){
        logger.info(userInfo.toString());
        try {
            userService.addUser(userInfo);
        } catch (IllegalArgumentException e){
            return new Response(false, e.getMessage());
        }
        return new Response(true,"Пользователь добавлен");
    }

    @PostMapping("/delete/user")
    public Response deleteUser(@RequestBody UserInfo userInfo){
        logger.info(userInfo.toString());
        try {
            userService.deleteUser(userInfo);
        } catch (NoSuchElementException e){
            return new Response(false, e.getMessage());
        }
        return new Response(true,"Пользователь удален");
    }
}
