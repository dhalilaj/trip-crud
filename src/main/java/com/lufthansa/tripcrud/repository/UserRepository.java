package com.lufthansa.tripcrud.repository;

import com.lufthansa.tripcrud.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findUserByUsername(String username);

    User findByUsername(String username);
    //List<Users> findAll();

    User findFirstByUsername(String username);

    boolean existsByUsername(String username);

}
