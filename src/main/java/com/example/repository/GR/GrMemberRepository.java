package com.example.repository.GR;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Member;

@Repository
public interface GrMemberRepository  extends JpaRepository<Member, String> {
    
    
}
