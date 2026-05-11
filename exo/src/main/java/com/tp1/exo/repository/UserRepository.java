package com.tp1.exo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tp1.exo.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // البحث عن المستخدم بواسطة اسم المستخدم
    Optional<User> findByUsername(String username);
}
