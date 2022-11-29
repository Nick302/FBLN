package com.example.freelancefbln.controller;

import com.example.freelancefbln.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    @GetMapping("/login")
    public String loginPage() {
        return "user/login";
    }


}
