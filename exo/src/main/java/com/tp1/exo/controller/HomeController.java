package com.tp1.exo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // الصفحة الرئيسية للموقع
    @GetMapping("/")
    public String home() {
        return "index";
    }
}
