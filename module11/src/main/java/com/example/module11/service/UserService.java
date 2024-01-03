package com.example.module11.service;

import com.example.module11.model.UserRepository;
import com.example.module11.model.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public void editUser(User user){
        userRepository.save(user);
    }
}
