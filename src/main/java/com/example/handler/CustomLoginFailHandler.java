package com.example.handler;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.example.dto.MemberDTO;
import com.example.entity.Member;
import com.example.service.AR.ArMemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CustomLoginFailHandler implements AuthenticationFailureHandler {

    private final ArMemberService mService; // ArMemberService 필드 추가

    public CustomLoginFailHandler(ArMemberService mService) {
        this.mService = mService; // ArMemberService 주입
    }


    @Override
    public void onAuthenticationFailure( HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {
              
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String errorMessage = "";
        log.info(email.toString());       
        log.info(password.toString());

        // Member member = mService.findByEmail(request.getParameter("email").toString());
        // log.info("Member=>{}", member.toString()); null일경우 이부분이 오류남 -> 주석처리 !!


        if (email.isEmpty()) {
        // 이메일을 입력하지 않은 경우
        errorMessage = "이메일을 입력해주세요 ";
        }
        else if (password.isEmpty()) {
            // 비밀번호를 입력하지 않은 경우
            errorMessage = "비밀번호를 입력해주세요 ";
        } 
        else if (!isValidEmailFormat(email)) {
        // 이메일 형식이 올바르지 않은 경우
        errorMessage = "이메일 형식을 확인해주세요 ";
        }
        else{
            // 이부분 email/password 나눠서 주고싶은데 제능력부족으로 .. 
            //그냥 db에 없는 계정이면 다 막습니다...
            errorMessage = "존재하지 않는 계정입니다";

        }
        
        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("alertTitle", "로그인 실패");
        httpSession.setAttribute("alertMessage", errorMessage);
        httpSession.setAttribute("alertUrl", "/login.do");
        
        response.sendRedirect(request.getContextPath() + "/alert.do");
        
    }

    private boolean isValidEmailFormat(String email) {
    // 이메일 형식이 올바른지 검사하는 로직을 구현합니다.
    // 예: 정규식 등을 사용하여 이메일 형식을 확인할 수 있습니다.
    // 유효한 이메일인 경우 true를 반환하고, 그렇지 않은 경우 false를 반환합니다.
    return email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
    }



}



