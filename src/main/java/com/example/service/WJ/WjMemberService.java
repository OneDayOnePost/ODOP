package com.example.service.WJ;

import org.springframework.stereotype.Service;

import com.example.entity.Member;

@Service
public interface WjMemberService {
    // 멤버 한 명 조회 (이메일)
    public Member findByEmail(String email);

    // 멤버 한 명 조회 (닉네임)
    public Member findByNickname(String nickname);
}
