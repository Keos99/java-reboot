package ru.edu.module12.model;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.edu.module12.model.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
