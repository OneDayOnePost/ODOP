package com.example.repository.MH;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entity.Image;

@Repository
public interface MhImageRepository extends JpaRepository<Image, BigInteger> {

    @Query(
        value="SELECT i.no FROM (SELECT i.*, ROW_NUMBER() OVER(ORDER BY i.no DESC) rnum FROM Image i WHERE email = :email ) i WHERE rnum Between 1 AND :count ORDER BY i.rnum ASC ", 
        nativeQuery = true) // :name (nativequery) = #{name} (mybatis) 
    List<Image> findRecentImageListByEmail(@Param("email") String email, @Param("count") int count);
    
}
