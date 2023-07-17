package com.example.repository.AR;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Member;

@Repository
public interface ArMemberRepository extends JpaRepository<Member, String> {
    


}
