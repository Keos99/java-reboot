package ru.sberbank.edu.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "users")
public class UserInfo {

    @Id
    private Long id;
    private String name;
    private Integer age;
    private String login;
    private String password;

    public UserInfo() {
    }

    public UserInfo(Long id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public UserInfo(Long id, String name, Integer age, String login, String password) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.login = login;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserInfo userInfo = (UserInfo) o;
        return Objects.equals(id, userInfo.id) && Objects.equals(name, userInfo.name) && Objects.equals(age, userInfo.age) && Objects.equals(login, userInfo.login) && Objects.equals(password, userInfo.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, login, password);
    }
}
