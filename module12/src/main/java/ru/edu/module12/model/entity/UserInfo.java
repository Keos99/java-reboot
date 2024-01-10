package ru.edu.module12.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

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
}
