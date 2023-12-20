package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller

public class HomeController {

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("pageTitle", "My Spring Boot Thymeleaf App");
        model.addAttribute("items", Arrays.asList("Item 1", "Item 2", "Item 3"));
        return "index";
    }
}

