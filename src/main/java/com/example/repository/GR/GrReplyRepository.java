package com.example.repository.GR;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Reply;

@Repository
public interface GrReplyRepository extends JpaRepository<Reply, BigInteger> {
    
}
