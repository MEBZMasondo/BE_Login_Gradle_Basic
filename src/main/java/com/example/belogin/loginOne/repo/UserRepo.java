package com.example.belogin.loginOne.repo;


import com.example.belogin.loginOne.util.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@EnableJpaRepositories
@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findOneByUsernameAndPassword(String username, String password);

    User findByUsername(String username);
}
