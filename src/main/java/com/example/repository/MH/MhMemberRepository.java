package com.example.repository.MH;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Member;

@Repository
public interface MhMemberRepository extends JpaRepository<Member, String> {
    
}
