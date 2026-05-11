package com.tp1.exo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tp1.exo.entity.User;
import com.tp1.exo.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    // Constructor Injection
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        // البحث عن المستخدم في قاعدة البيانات
        User user = userRepository.findByUsername(username).orElse(null);

        // إذا لم يتم العثور على المستخدم
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        // تحويل الأدوار إلى قائمة من الصلاحيات
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (var role : user.getRoles()) {
            authorities.add(
                new SimpleGrantedAuthority(role.name())
            );
        }

        // إنشاء كائن UserDetails وإرجاعه إلى Spring Security
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                authorities
        );
    }
}