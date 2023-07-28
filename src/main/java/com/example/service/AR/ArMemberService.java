package com.example.service.AR;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.example.entity.Member;

@Service
public interface ArMemberService {
    
    //회원가입
    public Member insertMember(Member member);

    //이메일 중복확인
    public int selectMemberEmailCheck(@Param("email") String email);

    //닉네임 중복확인
    public int selectMemberNicknameCheck(@Param("nickname") String nickname);

    //로그인
    public Member selectUserByEmail(String email);

}
