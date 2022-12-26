package com.example.prepractice.user.repository;

import com.example.prepractice.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User,Long> {
}
