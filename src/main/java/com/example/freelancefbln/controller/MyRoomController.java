package com.example.freelancefbln.controller;

import com.example.freelancefbln.dao.ReportRepository;
import com.example.freelancefbln.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/index")
@Controller
@RequiredArgsConstructor
public class MyRoomController {

    private final UserService userService;
    private final ReportRepository repository;

    @GetMapping()
    public String hello(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        model.addAttribute("report", repository.findAll());
        return "user/myroom";
    }

}
