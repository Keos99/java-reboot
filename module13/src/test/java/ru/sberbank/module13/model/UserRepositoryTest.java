package ru.sberbank.module13.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.edu.module12.model.UserRepository;
import ru.edu.module12.model.entity.UserInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    void findByLogin_WhenValidLogin_ShouldReturnUserInfo() {
        UserInfo userInfo = new UserInfo(4L,"Secret Admin", 999, "admin", "123456");
        userRepository.saveAll(Arrays.asList(userInfo));

        UserInfo foundUserInfo = userRepository.findByLogin("admin");

        assertNotNull(foundUserInfo);
        assertEquals(userInfo, foundUserInfo);
    }

    @Test
    void findByLogin_WhenInvalidLogin_ShouldReturnEmptyOptional() {
        String login = "invalidUser";

        UserInfo foundUserInfo = userRepository.findByLogin("newUser");

        assertNull(foundUserInfo);
    }

    @Test
    void findAll_WhenUsersExist_ShouldReturnAllUsers() {
        List<UserInfo> userInfos = new ArrayList<>();
        userInfos.add(new UserInfo(1L, "John Doe", 30));
        userInfos.add(new UserInfo(2L, "Jane Doe", 25));
        userInfos.add(new UserInfo(3L, "Peter Smith", 40));
        userRepository.saveAll(userInfos);

        List<UserInfo> allUsers = userRepository.findAll();

        assertEquals(userInfos, allUsers);
    }

    @Test
    void save_WhenUserIsValid_ShouldSaveUser() {
        UserInfo newUser = new UserInfo(6L,"newUser", 35, "newUser", "1");

        userRepository.save(newUser);

        UserInfo savedUserInfo = userRepository.findByLogin("newUser");
        assertNotNull(savedUserInfo);
        assertEquals(newUser, savedUserInfo);
    }
}
