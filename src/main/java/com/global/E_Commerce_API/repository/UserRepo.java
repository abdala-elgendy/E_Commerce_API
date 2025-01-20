package com.global.E_Commerce_API.repository;

import com.global.E_Commerce_API.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {

    User findByUsername(String username);


    User findUserByEmail(String email);

}
