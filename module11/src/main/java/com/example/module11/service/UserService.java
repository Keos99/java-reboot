package com.example.module11.service;

import com.example.module11.model.UserRepository;
import com.example.module11.model.entity.User;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

@Component
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        userRepository.saveAll(Arrays.asList(
                new User(1L, "John Doe", 30),
                new User(2L, "Jane Doe", 25),
                new User(3L, "Peter Smith", 40)
                ));
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public void editUser(User user) {
        userRepository.findById(user.getId()).orElseThrow(() -> new NoSuchElementException("Пользователь не найден"));
        userRepository.save(user);
    }

    public void addUser(User user) {
        if (userRepository.findById(user.getId()).isPresent()){
            throw new IllegalArgumentException("Пользователь с данным id уже существует");
        }
        userRepository.saveAll(Arrays.asList(
                user
        ));
    }

    public void deleteUser(User user) {
        userRepository.findById(user.getId()).orElseThrow(() -> new NoSuchElementException("Пользователь не найден"));
        userRepository.deleteById(user.getId());
    }
}
