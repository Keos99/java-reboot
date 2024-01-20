package ru.edu.module12.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.edu.module12.model.UserRepository;
import ru.edu.module12.model.entity.UserInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class UserServiceTest {

    private final UserRepository userRepositoryMock = Mockito.mock(UserRepository.class);

    private final UserService userService = new UserService(userRepositoryMock);

    @Test
    void getAllUsers() {
        List<UserInfo> expectedUsers = new ArrayList<>();
        expectedUsers.add(new UserInfo(1L, "John Doe", 30));
        expectedUsers.add(new UserInfo(2L, "Jane Doe", 25));
        expectedUsers.add(new UserInfo(3L, "Peter Smith", 40));
        expectedUsers.add(new UserInfo(4L, "Secret Admin", 999, "admin", "123456"));
        Mockito.when(userRepositoryMock.findAll()).thenReturn(expectedUsers);

        List<UserInfo> actualUsers = userService.getAll();

        assertEquals(expectedUsers, actualUsers);
    }

    @Test
    void editExistingUser() {
        UserInfo existingUser = new UserInfo(1L, "John Doe", 30);
        Mockito.when(userRepositoryMock.findById(existingUser.getId())).thenReturn(java.util.Optional.of(existingUser));

        boolean success = userService.editUser(existingUser);

        assertTrue(success);
        Mockito.verify(userRepositoryMock).save(existingUser);
    }

    @Test
    void editNonExistingUser() {
        UserInfo nonExistingUser = new UserInfo(5L, "New User", 20);

        Exception exception = assertThrows(NoSuchElementException.class, () -> userService.editUser(nonExistingUser));

        assertEquals("Пользователь не найден", exception.getMessage());
        Mockito.verify(userRepositoryMock, Mockito.never()).save(any());
    }

    @Test
    void addNewUser() {
        UserInfo newUser = new UserInfo(5L, "New User", 20);
        Mockito.when(userRepositoryMock.findById(newUser.getId())).thenReturn(java.util.Optional.empty());

        boolean success = userService.addUser(newUser);

        assertTrue(success);
        Mockito.verify(userRepositoryMock).saveAll(Arrays.asList(newUser));
    }

    @Test
    void deleteExistingUser() {
        UserInfo existingUser = new UserInfo(1L, "John Doe", 30);
        Mockito.when(userRepositoryMock.findById(existingUser.getId())).thenReturn(java.util.Optional.of(existingUser));

        boolean success = userService.deleteUser(existingUser);

        assertTrue(success);
        Mockito.verify(userRepositoryMock).deleteById(existingUser.getId());
    }

}