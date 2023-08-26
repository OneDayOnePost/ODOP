package com.example.repository.GR;

import java.math.BigInteger;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Dope;

@Repository
public interface GrDopeRepository extends JpaRepository<Dope, BigInteger> {
    
    // 삭제
    @Transactional
    void deleteByEmailAndPost_no(String email, BigInteger postno);

    Dope findByEmailAndPost_no(String email, BigInteger postno);

    int countByEmailAndPost_no(String email, BigInteger postno);

    // 좋아요 여부 확인
    boolean existsByEmailAndPost_no(String email, BigInteger postno);

    // 좋아요 갯수
    int countByPost_no(BigInteger postno);
}
