package com.example.controller.MH;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MhHomeController {
    
    @GetMapping(value = "/login.do")
    public String loginGET() {

        return "/MH/login";

    }
    
}
