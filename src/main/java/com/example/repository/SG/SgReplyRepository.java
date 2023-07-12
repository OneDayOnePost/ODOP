package com.example.repository.SG;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Reply;

@Repository
public interface SgReplyRepository extends JpaRepository<Reply, BigInteger> {
    
}
