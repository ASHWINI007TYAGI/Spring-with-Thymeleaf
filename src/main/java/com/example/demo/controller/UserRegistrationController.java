package com.example.demo.controller;

import com.example.demo.payload.UserRegistrationDto;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;


@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

    private UserService userService;

    public UserRegistrationController(UserService userService) {
        super();
        this.userService = userService;
    }

    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm() {
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user")@Valid UserRegistrationDto registrationDto,
                                      BindingResult result, RedirectAttributes redirectAttributes, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("registrationError", "Registration failed. Please check the form for errors.");
            return "registration";
        }

        try {
            userService.save(registrationDto);
            redirectAttributes.addFlashAttribute("successMessage", "User registered successfully!");
            return "redirect:/registration?success";
        } catch (ConstraintViolationException ex) {
            model.addAttribute("registrationError", ex.getMessage());
            return "registration";
        }
    }


}
