package com.example.repository.WJ;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Reply;

@Repository
public interface WjReplyRepository extends JpaRepository<Reply, BigInteger> {
    
}
