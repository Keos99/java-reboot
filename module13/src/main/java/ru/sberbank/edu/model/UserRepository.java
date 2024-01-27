package ru.sberbank.edu.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sberbank.edu.model.entity.UserInfo;


@Repository
public interface UserRepository extends JpaRepository<UserInfo, Long> {

    UserInfo findByLogin(String login);
}
