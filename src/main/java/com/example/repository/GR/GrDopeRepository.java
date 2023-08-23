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
}
