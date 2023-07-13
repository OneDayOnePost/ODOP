package com.example.controller.AR;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.entity.Member;

@Controller
public class ArHomeController {
    

    // 회원가입
    @GetMapping(value = "/join.do")
    public String joinGET(){
        try {
            return "/AR/join";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/odop/home.do";
        }
    }

    @PostMapping(value = "/join.do")
    public String joinPOST(@ModelAttribute Member member){
        try {
            
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    @GetMapping(value = "/login.do")
    public String loginGET() {

        return "/AR/login"; 

    }
}
