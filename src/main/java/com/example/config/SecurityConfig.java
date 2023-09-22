package com.example.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
// import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.example.handler.CustomLogoutSuccessHandler;
import com.example.service.CustomOAuth2UserServiceImpl;
import com.example.service.SecurityServiceImpl;
import com.example.service.AR.ArMemberService;

import lombok.RequiredArgsConstructor;

@Configuration // 환경설정파일 -> 서버가 구동되기 전에 호출됨
@EnableWebSecurity // 시큐리티를 사용
@Component // 빈으로 등록
@RequiredArgsConstructor
public class SecurityConfig {

    final private SecurityServiceImpl userLoginService;
    final private CustomOAuth2UserServiceImpl oAuth2UserService;
    private final AuthenticationFailureHandler customLoginFailHandler; // 핸들러 필드는 이렇게 선언하고, 생성자에서 주입하도록 수정


    @Bean // 객체를 생성(자동으로 호출됨)
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // 권한 설정
        http.authorizeRequests()
            .antMatchers("/admin", "/admin/*").hasAuthority("ROLE_A")
            .anyRequest().permitAll();

        // 403 페이지 설정(접근권한 불가 시 표시할 화면)
        http.exceptionHandling().accessDeniedPage("/403page.com");

        // (1) 로그인 처리
        http.formLogin()
            .loginPage("/login.do")
            .loginProcessingUrl("/loginaction.do")
            .usernameParameter("email")
            .passwordParameter("password")
            .defaultSuccessUrl("/home.do")
            // .successHandler(new LoginSuccessHandler())
            .failureHandler(customLoginFailHandler) // 로그인 실패 핸들러
            .permitAll();

        // (2) 로그아웃 처리 (get은 안됨. 반드시 post로 호출해야 됨)
        http.logout()
            .logoutUrl("/logout.do") // 로그아웃하는 주소
            // .logoutSuccessUrl("/home.com") // 로그아웃 성공시 이동할 페이지
            .logoutSuccessHandler(new CustomLogoutSuccessHandler())
            .invalidateHttpSession(true)
            .clearAuthentication(true)
            .permitAll();
        
        http.oauth2Login()
            .userInfoEndpoint()
            .userService(oAuth2UserService);

        // post는 csrf를 전송해야하지만, 주소가 /api로 시작하는 모든 url은 csrf가 없어도 허용하도록 설정
        http.csrf().ignoringAntMatchers("/api/**");
        // http.csrf().ignoringAntMatchers("/oauth2/**");

        http.userDetailsService(userLoginService);

        return http.build();
    }

    // 정적 자원에 스프링 시큐리티 필터 규칙을 적용하지 않도록 설정, resources/static은 시큐리티 적용받지 않음
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    // 로그인 시, 회원가입에서 사용했던 것과 같은 암호화 알고리즘 사용
    @Bean // 서버 구동시 자동으로 실행됨
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
