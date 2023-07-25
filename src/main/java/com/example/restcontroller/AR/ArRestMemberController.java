package com.example.restcontroller.AR;

import java.math.BigInteger;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.example.service.AR.ArMemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
@Slf4j
public class ArRestMemberController {
    final ArMemberService mService;

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



    // // 소셜 로그인 (카카오)
    // // view에서 받은 이메일이 기존 테이블에 있으면(ret=1) 회원가입 필요 X
    // //                                      없으면(ret=0) 회원가입 필요 O
    // @PostMapping(value = "/kakaologin.bubble")
    // public Map<String, Object> kakaologinPOST(@RequestBody Map<String, Object> map,
    //                                           HttpServletRequest request,
    //                                           HttpServletResponse response) {
    //     Map<String, Object> retMap = new HashMap<>();

    //     try {
    //         int emailchk = cService.countCustomerEmailCheck(map.get("email").toString());

    //         retMap.put("status", 200);

    //         // (2) 세션에 저장할 객체 생성하기 (저장할 객체, null)
    //         String[] strRole = {"ROLE_CUSTOMER"};
    //         Collection<GrantedAuthority> role = AuthorityUtils.createAuthorityList(strRole);

    //         // DetailsService를 사용하지 않고 세션에 저장하기
    //         if (emailchk == 1) { // 카카오 계정 이메일이 기존 테이블에 있는 경우
    //             // (1) 기존 자료 읽기
    //             Customer customer = cService.selectCustomerEmail(map.get("email").toString());

    //             if (customer.getGrade() != BigInteger.valueOf(0)) { // 탈퇴하지 않은 회원인 경우
    //                 // (2) 세션에 저장할 객체 생성하기 (저장할 객체, null)
    //                 User user = new User(customer.getId(), "", role);
    //                 UsernamePasswordAuthenticationToken authenticationToken 
    //                     = new UsernamePasswordAuthenticationToken(user, null, role);

    //                 // (3) 수동으로 세션에 저장(로그인)
    //                 SecurityContext context = SecurityContextHolder.createEmptyContext();
    //                 context.setAuthentication(authenticationToken);
    //                 SecurityContextHolder.setContext(context);
                    
    //                 retMap.put("ret", emailchk);
    //             }
    //             else { // 이미 탈퇴한 회원인 경우
    //                 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    //                 if (auth != null) {
    //                     new SecurityContextLogoutHandler().logout(request, response, auth);
    //                 }
                    
    //                 retMap.put("ret", -1);
    //             }
    //         }
    //         else { // 카카오 계정 이메일이 기존 테이블에 없는 경우
    //             // (2) 세션에 저장할 객체 생성하기 (저장할 객체, null)
    //             User user = new User(map.get("id").toString(), "", role);
    //             UsernamePasswordAuthenticationToken authenticationToken 
    //                 = new UsernamePasswordAuthenticationToken(user, null, role);

    //             // (3) 수동으로 세션에 저장(로그인)
    //             SecurityContext context = SecurityContextHolder.createEmptyContext();
    //             context.setAuthentication(authenticationToken);
    //             SecurityContextHolder.setContext(context);

    //             retMap.put("ret", emailchk);
    //         }
    //     }
    //     catch (Exception e) {
    //         e.printStackTrace();
    //         retMap.put("status", -1);
    //         retMap.put("error", e.getMessage());
    //     }
    //     return retMap;
    // }
}
