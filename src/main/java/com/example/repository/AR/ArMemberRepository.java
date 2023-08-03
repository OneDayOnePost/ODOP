package com.example.repository.AR;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.dto.MemberDTO;
import com.example.entity.Member;


@Repository
public interface ArMemberRepository extends JpaRepository<Member, String> {
    
    //이메일 중복 여부
    int countByEmail(String email);

    //닉네임 중복 여부
    int countByNickname(String nickname);

    //로그인 관련
    Member findByEmail(String email);

    //아이디 찾기
    Member findByNameAndPhone(String name, String phone);

}
