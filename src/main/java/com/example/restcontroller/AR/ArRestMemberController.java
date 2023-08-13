package com.example.restcontroller.AR;

import java.math.BigInteger;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.AR.ArMailService;
import com.example.service.AR.ArMemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
@Slf4j
public class ArRestMemberController {
    final ArMemberService mService;
    final ArMailService mailService;

    // 아이디 중복 확인
    // 127.0.0.1:5059/odop/api/emailcheck.do?email=이메일
    @GetMapping(value = "/emailcheck.do")
    public Map<String, Object> emailcheckGET(@RequestParam(name = "email") String email) {
        Map<String, Object> retMap = new HashMap<>();

        try {
            retMap.put("status", 200);
            retMap.put("ret", mService.selectMemberEmailCheck(email));
        }
        catch (Exception e) {
            e.printStackTrace();
            retMap.put("status", -1);
            retMap.put("error", e.getMessage());
        }

        return retMap;
    }



    //닉네임 중복 확인
    // 127.0.0.1:5059/odop/api/nickcheck.do?nickname=닉네임
    @GetMapping(value="/nickcheck.do")
    public Map<String, Object> nickcheckGET(@RequestParam(name="nickname") String nickname){
        Map<String, Object> retMap = new HashMap<>();
        
                                                
        try{
            retMap.put("status",200);
            retMap.put("ret",mService.selectMemberNicknameCheck(nickname));

        }
        catch(Exception e){
            e.printStackTrace();
            retMap.put("status", -1);
            retMap.put("error", e.getMessage());
        }
        return retMap;
    }



    //이메일 인증 코드 발송
    @PostMapping("/emailconfirm.do")
    public String emailConfirm(@RequestParam(name="email") String email) throws Exception {

    String confirm = mailService.sendSimpleMessage(email);   
    Map<String, Object> retMap = new HashMap<>();
      try{
            retMap.put("status",200);
            retMap.put("ret",confirm);
            log.info(confirm);

        }
        catch(Exception e){
            e.printStackTrace();
            retMap.put("status", -1);
            retMap.put("error", e.getMessage());
            
        }
        return confirm;
    }

}



