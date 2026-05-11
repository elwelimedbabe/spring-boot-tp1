package com.tp1.exo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.tp1.exo.service.CustomUserDetailsService;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;
    private final PasswordEncoder passwordEncoder;

    // Constructor Injection
    public SecurityConfig(CustomUserDetailsService customUserDetailsService,
                          PasswordEncoder passwordEncoder) {
        this.customUserDetailsService = customUserDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    // Authentication Provider
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider =
                new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(customUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);

        return authProvider;
    }

    // Security Filter Chain
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http
            .authenticationProvider(authenticationProvider())

            .authorizeHttpRequests(auth -> auth

                // الملفات العامة
                .requestMatchers(
                        "/",
                        "/login",
                        "/css/**",
                        "/js/**",
                        "/images/**"
                ).permitAll()

                // صفحات المنتجات للمدير فقط
                .requestMatchers(
                        "/products/new",
                        "/products/save",
                        "/products/edit/**",
                        "/products/delete/**"
                ).hasRole("ADMIN")

                // صفحات المقالات للمدير فقط
                .requestMatchers(
                        "/articles/new",
                        "/articles/save",
                        "/articles/edit/**",
                        "/articles/delete/**"
                ).hasRole("ADMIN")

                // حذف التعليقات للمدير فقط
                .requestMatchers(
                        "/comments/delete/**"
                ).hasRole("ADMIN")

                // إضافة التعليقات للمستخدمين المسجلين
                .requestMatchers(
                        "/comments/**"
                ).hasAnyRole("USER", "ADMIN")

                // عرض المنتجات والمقالات للمستخدمين المسجلين
                .requestMatchers(
                        "/products/**",
                        "/articles/**"
                ).hasAnyRole("USER", "ADMIN")

                // أي طلب آخر يحتاج تسجيل الدخول
                .anyRequest().authenticated()
            )

            // صفحة تسجيل الدخول
            .formLogin(form -> form
                    .loginPage("/login")
                    .defaultSuccessUrl("/", true)
                    .permitAll()
            )

            // تسجيل الخروج
            .logout(logout -> logout
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login")
                    .permitAll()
)

            // صفحة رفض الوصول
            .exceptionHandling(exception -> exception
                    .accessDeniedPage("/access-denied")
            );

        return http.build();
    }
}