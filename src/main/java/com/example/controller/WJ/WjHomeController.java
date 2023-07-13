package com.example.controller.WJ;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping
@RequiredArgsConstructor
public class WjHomeController {
    @GetMapping(value = "/adminhome.do")
    public String homeGET(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        return "/WJ/WjAdminPostReport";
    }
}
