package com.example.repository.MH;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Reply;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, BigInteger> {

    // 댓글 조회
    List<Reply> findByPost_noAndRepdepthAndState(BigInteger postno, BigInteger repdepth, BigInteger state, Sort sort);

    // 답글 조회
    List<Reply> findByPost_noAndRepdepthAndStateAndRepgroup(BigInteger postno, BigInteger repdepth, BigInteger state, BigInteger repgroup, Sort sort);

    // 댓글 개수
    int countByPost_noAndRepdepthAndState(BigInteger postno, BigInteger repdepth, BigInteger state);
    
}
