package ru.sberbank.module13.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.edu.module12.controller.UserController;
import ru.edu.module12.model.dto.Response;
import ru.edu.module12.model.entity.UserInfo;
import ru.edu.module12.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserControllerTest {

    @Test
    public void getUsers_whenNoUsersExist_shouldReturnEmptyList() throws Exception {
        UserController controller = new UserController(mock(UserService.class));

        List<UserInfo> users = controller.getUsers();

        Assertions.assertEquals(0, users.size());
    }

    @Test
    public void getUsers_whenUsersExist_shouldReturnListOfUsers() throws Exception {
        List<UserInfo> users = new ArrayList<>();
        users.add(new UserInfo(1L,"John Doe", 30));
        users.add(new UserInfo(2L, "Jane Doe", 25));

        UserService userService = mock(UserService.class);
        when(userService.getAll()).thenReturn(users);

        UserController controller = new UserController(userService);

        List<UserInfo> returnedUsers = controller.getUsers();

        Assertions.assertEquals(users, returnedUsers);
    }

    @Test
    public void editUser_whenUserExists_shouldUpdateUser() throws Exception {
        UserInfo user = new UserInfo(1L,"John Doe", 30);

        UserService userService = mock(UserService.class);
        when(userService.editUser(user)).thenReturn(true);

        UserController controller = new UserController(userService);

        Response response = controller.editUser(user);

        Assertions.assertEquals(true, response.isSucsess());
        Assertions.assertEquals("Пользователь отредактирован", response.getMessage());
    }

    @Test
    public void editUser_whenUserDoesNotExist_shouldReturnErrorMessage() throws Exception {
        UserInfo user = new UserInfo(1L,"John Doe", 30);

        UserService userService = mock(UserService.class);
        when(userService.editUser(user)).thenThrow(new NoSuchElementException("Пользователь не найден"));

        UserController controller = new UserController(userService);

        Response response = controller.editUser(user);

        Assertions.assertEquals(false, response.isSucsess());
        Assertions.assertEquals("Пользователь не найден", response.getMessage());
    }

    @Test
    public void addUser_whenUserIsValid_shouldAddUser() throws Exception {
        UserInfo user = new UserInfo(1L,"John Doe", 30);

        UserService userService = mock(UserService.class);
        when(userService.addUser(user)).thenReturn(true);

        UserController controller = new UserController(userService);

        Response response = controller.addUser(user);

        Assertions.assertEquals(true, response.isSucsess());
        Assertions.assertEquals("Пользователь добавлен", response.getMessage());
    }

    @Test
    public void addUser_whenUserIsInvalid_shouldReturnErrorMessage() throws Exception {
        UserInfo user = new UserInfo(1L,"John", 0);

        UserService userService = mock(UserService.class);
        when(userService.addUser(user)).thenThrow(new IllegalArgumentException("Пользователь с данным id уже существует"));

        UserController controller = new UserController(userService);

        Response response = controller.addUser(user);

        Assertions.assertEquals(false, response.isSucsess());
        Assertions.assertEquals("Пользователь с данным id уже существует", response.getMessage());
    }

    @Test
    public void deleteUser_whenUserExists_shouldDeleteUser() throws Exception {
        UserInfo user = new UserInfo(1L,"John Doe", 30);

        UserService userService = mock(UserService.class);
        when(userService.deleteUser(user)).thenReturn(true);

        UserController controller = new UserController(userService);

        Response response = controller.deleteUser(user);

        Assertions.assertEquals(true, response.isSucsess());
        Assertions.assertEquals("Пользователь удален", response.getMessage());
    }
}