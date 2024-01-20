package ru.edu.module12.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.edu.module12.model.entity.UserInfo;

@Repository
public interface UserRepository extends JpaRepository<UserInfo, Long> {

    UserInfo findByLogin(String login);
}
