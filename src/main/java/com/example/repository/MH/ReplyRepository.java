package com.example.repository.MH;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Reply;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, BigInteger> {

    List<Reply> findByPost_noOrderByRepgroupDesc(BigInteger postno);

    int countByPost_no(BigInteger postno);
    
}
