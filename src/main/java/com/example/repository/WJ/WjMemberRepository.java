package com.example.repository.WJ;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Member;

@Repository
public interface WjMemberRepository  extends JpaRepository<Member, String> {
    // 멤버 한 명 조회 (이메일)
    Member findByEmail(String email);

    // 멤버 한 명 조회 (닉네임)
    Member findByNickname(String nickname);
}
