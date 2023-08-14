package com.example.repository.GR;

import java.math.BigInteger;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.entity.Post;

@Repository
public interface GrPostRepository extends JpaRepository<Post, BigInteger> {

    List<Post> findByWriterOrderByNoDesc(String writer);

    List<Post> findByNoOrderByRegdateDesc(BigInteger postno);

    // 카테고리 별 조회
    List<Post> findByWriterAndCateNoOrderByNoDesc(String writer, BigInteger cateno);
    // @Query("SELECT p FROM Post p WHERE p.writer = :writer AND p.cate.no = :cateNo")
    // List<Post> findByWriterAndCateNo(@Param("writer") String writer, @Param("cateNo") BigInteger cateNo);

}