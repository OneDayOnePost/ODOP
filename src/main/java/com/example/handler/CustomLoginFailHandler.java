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
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.example.dto.MemberDTO;

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
        MemberDTO member = mService.findByEmail(request.getParameter("email").toString());

        
        if (password.isEmpty()) {
            // 비밀번호를 입력하지 않은 경우
            errorMessage = "비밀번호를 입력해주세요 ";
        } 
        if (member == null) {
        // DB에 해당 이메일이 없는 경우
        errorMessage = "이메일을 확인해주세요";
            if (email.isEmpty()) {
            // 이메일을 입력하지 않은 경우
            errorMessage = "이메일을 입력해주세요 ";
            }
            else if (!isValidEmailFormat(email)) {
                // 이메일 형식이 올바르지 않은 경우
            errorMessage = "이메일 형식을 확인해주세요 ";
            }
             // DB에서 가져온 비밀번호와 입력된 비밀번호를 비교합니다.
            if (!isPasswordMatch(member.getPassword(), password)) { //이부분에서 패스워드 .. 못불러와 ..힝
                // 비밀번호가 일치하지 않는 경우
                errorMessage = "비밀번호를 확인해주세요 ";
            } else {
                // 그 외의 로그인 실패 경우
                errorMessage = "알 수 없는 오류입니다. 잠시 후 시도해주세요";
            }
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

    private boolean isPasswordMatch(String dbPassword, String password) {
    // DB에서 가져온 비밀번호와 입력된 비밀번호를 비교하는 로직을 구현합니다.
    // 예: BCrypt 등을 사용하여 비밀번호를 비교할 수 있습니다.
    // 비밀번호가 일치하는 경우 true를 반환하고, 그렇지 않은 경우 false를 반환합니다.
    return dbPassword.equals(password);
    }


}

