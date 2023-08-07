package com.example.mapper.WJ;


import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.entity.Follow;

@Mapper
public interface WjMyblogMapper {
    // 팔로우 유무 확인
    @Select({" SELECT * FROM follow WHERE myid = #{useremail} AND yourid = #{blogemail} "})
    public Follow selectFollow(@Param("useremail") String useremail, @Param("blogemail") String blogemail);

    // 언팔로우
    @Delete({" DELETE FROM follow WHERE myid = #{useremail} AND yourid = #{blogemail} "})
    public int UnFollow(@Param("useremail") String useremail, @Param("blogemail") String blogemail);

    // 팔로우
    @Insert({" INSERT INTO follow (myid, yourid) VALUES (#{useremail}, #{blogemail}) "})
    public int Follow(@Param("useremail") String useremail, @Param("blogemail") String blogemail);
}
