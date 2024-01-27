package ru.edu.module12.service;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import ru.edu.module12.model.UserRepository;
import ru.edu.module12.model.entity.UserInfo;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;

        userRepository.saveAll(Arrays.asList(
                new UserInfo(1L, "John Doe", 30),
                new UserInfo(2L, "Jane Doe", 25),
                new UserInfo(3L, "Peter Smith", 40),
                new UserInfo(4L, "Secret Admin", 999, "admin", "123456")
        ));
    }

    public List<UserInfo> getAll() {
        return userRepository.findAll();
    }

    public boolean editUser(UserInfo userInfo) {
        userRepository.findById(userInfo.getId()).orElseThrow(() -> new NoSuchElementException("Пользователь не найден"));
        userRepository.save(userInfo);
        return true;
    }

    public boolean addUser(UserInfo userInfo) {
        if (userRepository.findById(userInfo.getId()).isPresent()){
            throw new IllegalArgumentException("Пользователь с данным id уже существует");
        }
        userRepository.saveAll(Arrays.asList(
                userInfo
        ));
        return true;
    }

    public boolean deleteUser(UserInfo userInfo) {
        userRepository.findById(userInfo.getId()).orElseThrow(() -> new NoSuchElementException("Пользователь не найден"));
        userRepository.deleteById(userInfo.getId());
        return true;
    }

    public UserInfo getUserByLogin(String login){
        return userRepository.findByLogin(login);
    }
}
