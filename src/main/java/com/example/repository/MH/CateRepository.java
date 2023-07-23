package com.example.repository.MH;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Cate;

@Repository
public interface CateRepository extends JpaRepository<Cate, BigInteger> {
    
}
