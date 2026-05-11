package com.tp1.exo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    // عرض صفحة تسجيل الدخول المخصصة
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // صفحة رفض الوصول (اختيارية)
    @GetMapping("/access-denied")
    public String accessDenied() {
        return "access-denied";
    }
}
