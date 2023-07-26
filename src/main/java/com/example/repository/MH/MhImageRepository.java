package com.example.repository.MH;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Image;

@Repository
public interface MhImageRepository extends JpaRepository<Image, BigInteger> {
    
}
