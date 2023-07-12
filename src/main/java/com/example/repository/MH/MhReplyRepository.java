package com.example.repository.MH;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Reply;

@Repository
public interface MhReplyRepository extends JpaRepository<Reply, BigInteger> {
    
}
