package com.example.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.entity.Member;
import com.example.repository.MH.MhMemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// (1) 로그인에서 로그인 버튼 -> loadUserByusername으로 이메일 정보를 넘김
@Service
@Slf4j
@RequiredArgsConstructor
public class SecurityServiceImpl implements UserDetailsService {

    final String format = "SecurityServiceImpl => {}";
    final private MhMemberRepository mRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // (2) Member 테이블에서 정보를 꺼낸 후 User 타입으로 변환해서 리턴
        // security가 비교 후에 로그인 처리를 자동으로 수행

        // log.info(format, username);

        Member obj = mRepository.findById(username).orElse(null);

        // 아이디가 있는 경우
        if (obj != null) { 
            if (obj.getPassword() != null) {
                return User.builder()
                        .username(obj.getEmail())
                        .password(obj.getPassword())
                        .roles("C")
                        .build();
            }
        }

        // 아이디가 없는 경우
        return User.builder()
                .username("_")
                .password("_")
                .roles("_")
                .build();
    }

}
