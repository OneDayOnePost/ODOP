package com.example.repository.MH;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Member;

public interface MhMemberRepository extends JpaRepository<Member, String> {
    
}
