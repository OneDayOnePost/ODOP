package com.example.service.AR;



import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.example.dto.MemberDTO;
import com.example.entity.Member;

@Service
public interface ArMemberService {
    
    //회원가입
    public Member insertMember(Member member);

    //이메일 중복확인
    public int selectMemberEmailCheck(@Param("email") String email);

    //닉네임 중복확인
    public int selectMemberNicknameCheck(@Param("nickname") String nickname);

    //로그인 관련
    public Member findByEmail(@Param("email") String email);

    //아이디 찾기 findByEmailAndNameAndPhone
    public Member selectMemberEmail(@Param("name") String name, @Param("phone") String phone);

    //비밀번호 찾기 및 변경
    public int updateMemberPassword(@Param("member") MemberDTO member);

    //이메일 찾기
    public List<MemberDTO> selectMemberEmail(@Param("email") String email);

    

}
