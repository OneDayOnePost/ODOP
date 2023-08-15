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
import org.springframework.web.bind.annotation.ResponseBody;
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


    // 인증번호 저장
    private Map<String, String> verificationCodes = new HashMap<>();

    //이메일 인증 코드 발송
    @PostMapping("/emailconfirm.do")
    public Map<String, Object> emailConfirm(@RequestParam(name="email") String email) throws Exception {

    String confirm = mailService.sendSimpleMessage(email);  

    // 생성된 인증번호를 저장
    verificationCodes.put(email, confirm); 

    Map<String, Object> retMap = new HashMap<>();
      try{
            retMap.put("status",200);
            retMap.put("ret",confirm);
            

        }
        catch(Exception e){
            e.printStackTrace();
            retMap.put("status", -1);
            retMap.put("error", e.getMessage());
            
        }
        return retMap;
    }


    @PostMapping("/verifycode.do")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> verifyCode(@RequestBody Map<String, String> requestData) {
        String email = requestData.get("email");
        String inputCode = requestData.get("verificationCode"); //입력될 값
        String code = verificationCodes.get(email); // 저장된 인증번호 가져오기
        log.info("code=>{}", inputCode);
        Map<String, Object> response = new HashMap<>();
        
        if (code != null && inputCode != null && inputCode.equals(code)) {
            // 이 부분은 인증번호가 일치하는 경우의 처리입니다.
            response.put("status", 200);
            response.put("message", "인증번호가 일치합니다.");
        } else {
            // 이 부분은 인증번호가 일치하지 않는 경우의 처리입니다.
            response.put("status", 400);
            response.put("message", "인증번호가 일치하지 않습니다.");
        }
        
    
        return ResponseEntity.ok(response);
    }
    

}



