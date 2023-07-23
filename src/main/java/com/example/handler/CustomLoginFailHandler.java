package com.example.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class CustomLoginFailHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {
        
        String errorMessage = "";

        if(exception instanceof UsernameNotFoundException) {
            errorMessage = "존재하지 않는 아이디입니다.";
        }
        else if(exception instanceof BadCredentialsException) {
            errorMessage = "아이디 또는 비밀번호를 잘못 입력하셨습니다.";
        }

        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("alertTitle", "로그인 실패");
        httpSession.setAttribute("alertMessage", errorMessage);
        httpSession.setAttribute("alertUrl", "/login.do");
        
        response.sendRedirect(request.getContextPath() + "/alert.do");
        
    }
    
}
