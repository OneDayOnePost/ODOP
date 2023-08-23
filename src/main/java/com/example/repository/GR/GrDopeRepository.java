package com.example.repository.GR;

import java.math.BigDecimal;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Dope;

@Repository
public interface GrDopeRepository extends JpaRepository<Dope, BigDecimal> {
    
    // 삭제
    @Transactional
    void deleteByEmailAndPost_no(String email, BigDecimal postno);

    Dope findByEmailAndPost_no(String email, BigDecimal postno);

    int countByEmailAndPost_no(String email, BigDecimal postno);
}
