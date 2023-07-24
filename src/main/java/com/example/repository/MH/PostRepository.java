package com.example.repository.MH;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, BigInteger> {
    
    List<Post> findByWriter(String writer);

    Post findTopByWriterOrderByNoDesc(String writer);

    @Query(
        value="SELECT * FROM (SELECT p.*, ROW_NUMBER() OVER(ORDER BY p.no DESC) rnum FROM POST p WHERE writer = :writer ) p WHERE rnum = 1 ORDER BY p.rnum ASC ", 
        nativeQuery = true) // :name (nativequery) = #{name} (mybatis) 
    Post findRecentPostByWriter(String writer);

}
