package com.example.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

public class CustomLoginFailHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure( HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {
              
              
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String errorMessage = "";
     
        if (email.isEmpty()) {
            // 이메일을 입력하지 않은 경우
            errorMessage = "이메일을 입력해주세요 ";
        } else if (!isValidEmailFormat(email)) {
            // 이메일 형식이 올바르지 않은 경우
           errorMessage = "이메일 형식을 확인해주세요 ";
        } else if (password.isEmpty()) {
            // 비밀번호를 입력하지 않은 경우
            errorMessage = "비밀번호를 입력해주세요 ";
        } else if (!isValidPassword(email, password)) {
            // 이메일과 비밀번호가 일치하지 않는 경우
           errorMessage = "이메일과 비밀번호를 확인해주세요 ";
        } else {
            // 그 외의 로그인 실패 경우
           errorMessage = "알 수 없는 오류 입니다. 잠시 후 시도해주세요";
        }


        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("alertTitle", "로그인 실패");
        httpSession.setAttribute("alertMessage", errorMessage);
        httpSession.setAttribute("alertUrl", "/login.do");
        
        response.sendRedirect(request.getContextPath() + "/alert.do");
        
    }
    
}


        
    }

    private boolean isValidEmailFormat(String email) {
        // 이메일 형식이 올바른지 검사하는 로직을 구현합니다.
        // 예: 정규식 등을 사용하여 이메일 형식을 확인할 수 있습니다.
        // 유효한 이메일인 경우 true를 반환하고, 그렇지 않은 경우 false를 반환합니다.
        return email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
    }

    private boolean isValidPassword(String email, String password) {
        // 이메일과 비밀번호가 일치하는지 검사하는 로직을 구현합니다.
        // 예: DB 조회 등을 사용하여 이메일과 비밀번호가 일치하는지 확인할 수 있습니다.
        // 일치하는 경우 true를 반환하고, 그렇지 않은 경우 false를 반환합니다.
        // 이 예제에서는 이메일이 "test@example.com", 비밀번호가 "123456"인 경우에만 로그인 성공으로 가정합니다.
        return email.equals("test@example.com") && password.equals("123456");
    }
}
