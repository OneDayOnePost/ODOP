package com.example.repository.GR;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.ReplyReason;

@Repository
public interface GrReplyReasonRepository extends JpaRepository<ReplyReason, BigInteger>{
    
}
