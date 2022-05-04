package com.example.kiennt_demo2.repository;

import com.example.kiennt_demo2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findOneByUsernameIgnoreCaseAndPassword(String username, String password);


    User findById(long id);

    Optional<User> findByUsername(String username);

    @Query(value = "SELECT u FROM User u " +
            "WHERE u.username = :username")
    User findByUsername2(String username);
    //List<User> findAll();
}
