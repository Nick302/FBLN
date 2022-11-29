package com.example.freelancefbln.controller;

import com.example.freelancefbln.entity.Role;
import com.example.freelancefbln.entity.User;
import com.example.freelancefbln.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.groups.Default;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class ThymeController {

    private final UserService userService;

    @ExceptionHandler(Exception.class)
    public String handleExceptions(Exception exception, HttpServletRequest request) {
        return "error/error500";
    }

    @GetMapping("/registration")
    public String registration(Model model, @ModelAttribute("user") User user) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "user/registration";
    }

    @PostMapping
    public String create(@ModelAttribute @Validated({Default.class}) User user,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("user", user);
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/users/registration";
        }
        userService.save(user);
        return "redirect:/login";
    }

}
