package com.tp1.exo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tp1.exo.entity.User;
import com.tp1.exo.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // جلب جميع المستخدمين
    public List<User> findAll() {
        return userRepository.findAll();
    }

    // جلب مستخدم حسب ID
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    // البحث عن مستخدم حسب اسم المستخدم
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // حفظ مستخدم
    public User save(User user) {
        return userRepository.save(user);
    }

    // حذف مستخدم
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
