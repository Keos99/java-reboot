package ru.edu.module12.service;


import org.springframework.stereotype.Component;
import ru.edu.module12.model.UserRepository;
import ru.edu.module12.model.entity.User;

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

    public boolean editUser(User user) {
        userRepository.findById(user.getId()).orElseThrow(() -> new NoSuchElementException("Пользователь не найден"));
        userRepository.save(user);
        return true;
    }

    public boolean addUser(User user) {
        if (userRepository.findById(user.getId()).isPresent()){
            throw new IllegalArgumentException("Пользователь с данным id уже существует");
        }
        userRepository.saveAll(Arrays.asList(
                user
        ));
        return true;
    }

    public boolean deleteUser(User user) {
        userRepository.findById(user.getId()).orElseThrow(() -> new NoSuchElementException("Пользователь не найден"));
        userRepository.deleteById(user.getId());
        return true;
    }
}
