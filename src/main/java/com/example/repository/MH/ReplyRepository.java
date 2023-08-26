package com.example.repository.MH;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Reply;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, BigInteger> {

    List<Reply> findByPost_noAndRepdepthAndStateOrderByRepgroupDesc(BigInteger postno, BigInteger repdepth, BigInteger state);

    int countByPost_noAndRepdepthAndState(BigInteger postno, BigInteger repdepth, BigInteger state);
    
}
