package com.example.repository.MH;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.PostTag;

@Repository
public interface PostTagRepository extends JpaRepository<PostTag, BigInteger> {
    
}
