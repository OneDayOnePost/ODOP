package com.example.repository.GR;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Post;


@Repository
public interface GrPostRepository extends JpaRepository<Post, BigInteger> {

    List<Post> findByWriterOrderByNoDesc(String writer);

    List<Post> findByPostTagListTag(String tag);

    // 카테고리 별 조회
    List<Post> findByWriterAndCateNoOrderByNoDesc(String writer, BigInteger cateno);

}