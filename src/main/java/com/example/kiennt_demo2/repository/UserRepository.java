package com.example.kiennt_demo2.repository;

import com.example.kiennt_demo2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findOneByUsernameIgnoreCaseAndPassword(String username, String password);

    @Query("From User WHERE username=:username")
    User findByUsername(String username);

    User findById(long id);



    //List<User> findAll();
}
