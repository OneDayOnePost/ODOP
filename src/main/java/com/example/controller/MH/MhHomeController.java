package com.example.controller.MH;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MhHomeController {
    
    @GetMapping(value = "/mhlogin.do")
    public String loginGET() {

        return "/MH/login";

    }

    @GetMapping(value = "/mhtest.do")
    public String testGET() {

        return "/fragments/header";

    }

    @GetMapping(value = "/mhhome.do")
    public String homeGET() {
        return "/MH/Mhhome";
    }

    @GetMapping(value = "/mhtest1.do")
    public String test1GET() {
        return "/MH/Mhtest";
    }
    
}
