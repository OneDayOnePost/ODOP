package com.example.service.AR;

import org.springframework.stereotype.Service;

import com.example.entity.Member;

@Service
public interface ArMemberService {
    
    //회원가입
    public Member insertMember(Member member);


}
