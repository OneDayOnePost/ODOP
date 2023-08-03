package com.example.controller.AR;


import javax.servlet.http.HttpSession;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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




    //아이디 찾기----------------------------------------
    @GetMapping(value = "/findemail.do")
    public String findemailGET() {

        return "/AR/findemail"; 

    }


    @PostMapping(value = "findemail.do")
    public String findemailPOST(@RequestParam("name") String name, @RequestParam("phone") String phone, HttpSession httpSession ){
        try{
            Member member = mService.selectMemberEmail(name, phone);

            //이메일 마스킹에 필요함
            int atIndex = member.getEmail().toString().indexOf('@');
           
            

            
            if (member != null) {

                if(atIndex <= 3){
                    String shortemail = member.getEmail().toString().charAt(0) + "*****" + member.getEmail().toString().substring(atIndex);
                    httpSession.setAttribute("alertTitle",member.getName()+"님의 아이디는");                
                    httpSession.setAttribute("alertMessage",shortemail+ " 입니다 ");
                    httpSession.setAttribute("alertUrl", "/login.do");
                    return "redirect:/alert.do";    
                }
                else{
                    int lenToMask = Math.min(atIndex - 3, 5);
                    String mask = "*".repeat(lenToMask);
                    String email = member.getEmail().toString().substring(0, 3) + mask + member.getEmail().toString().substring(atIndex);

                    httpSession.setAttribute("alertTitle",member.getName()+"님의 아이디는");                
                    httpSession.setAttribute("alertMessage",email+ " 입니다 ");
                    httpSession.setAttribute("alertUrl", "/login.do");
                    return "redirect:/alert.do";    
                }
               
            }
            else {
                httpSession.setAttribute("alertTitle", "존재 하지 않는 정보입니다.");                
                httpSession.setAttribute("alertMessage", "가입 후 로그인 해주세요.");
                httpSession.setAttribute("alertUrl", "/login.do");
                return "redirect:/alert.do";    
            }

         
        }
        catch(Exception e){
            e.printStackTrace();
            return "redirect:/findemail.do";
        }
    }




    //비밀번호 찾기---------------------------
    @GetMapping(value = "/findpw.do")
    public String findpwGET(){
      return "/AR/findpw";
    }


    @PostMapping(value = "/findpw.do")
    public String findpwPOST(){
        try {
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/findpw.do";
        }
    }

}



