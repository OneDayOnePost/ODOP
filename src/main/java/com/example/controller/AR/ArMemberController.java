package com.example.controller.AR;


import javax.servlet.http.HttpSession;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.entity.Member;
import com.example.mapper.AR.ArMemberMapper;
import com.example.service.AR.ArMemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@RequestMapping
@Slf4j
@RequiredArgsConstructor
@Controller
public class ArMemberController {
    
    // final ArMemberMapper mMapper;
    final ArMemberService mService;

    //암호화
    BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();



    //로그인----------------------------------------
    @GetMapping(value = "/login.do")
    public String loginGET() {

        return "/AR/login"; 

    }


    // 회원가입----------------------------------------
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
    public String joinPOST(@ModelAttribute Member member, HttpSession httpSession){
        try {
            log.info("MemberController => {} ", member.toString());


            member.setPassword(bcpe.encode(member.getPassword()));
            log.info("MemberController2 => {} ", member.toString());
            Member ret = mService.insertMember(member);
            
            if(ret != null){
                httpSession.setAttribute("alertTitle", "회원 가입");                
                httpSession.setAttribute("alertMessage", "가입 성공하였습니다 :)");
                httpSession.setAttribute("alertUrl", "/login.do");
                return "redirect:/alert.do";    
            }
            else{
                httpSession.setAttribute("alertTitle", "회원 가입");                
                httpSession.setAttribute("alertMessage", "가입 실패하였습니다 :(");
                httpSession.setAttribute("alertUrl", "/join.do");
                return "redirect:/alert.do";
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return "redirect:/login.do"; // 임시로 로그인창으로 해둠
        }

    }





}



