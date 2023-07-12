package com.example.repository.AR;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Reply;

@Repository
public interface ArReplyRepository extends JpaRepository<Reply, BigInteger> {
    
}
