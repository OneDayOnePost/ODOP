package com.example.repository.MH;

import java.math.BigInteger;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.PostTag;
import com.example.entity.PostTagProjection;

@Repository
public interface PostTagRepository extends JpaRepository<PostTag, BigInteger> {

    /* 해시태그 목록 호출 */
    List<PostTagProjection> findByPost_no(BigInteger no);

    /* 글 번호로 해시태그 전체 삭제*/
    @Transactional
    int deleteByPost_no(BigInteger no);
    
}
