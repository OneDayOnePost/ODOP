package com.example.mapper.WJ;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.dto.MemberFollowDTO;
import com.example.entity.Follow;

@Mapper
public interface WjMyblogMapper {
    // 팔로우 유무 확인
    @Select({" SELECT * FROM follow WHERE myid = #{useremail} AND yourid = #{blogemail} "})
    public Follow selectFollow(@Param("useremail") String useremail, @Param("blogemail") String blogemail);

    // 팔로우 취소
    @Delete({" DELETE FROM follow WHERE myid = #{useremail} AND yourid = #{blogemail} "})
    public int UnFollow(@Param("useremail") String useremail, @Param("blogemail") String blogemail);

    // 팔로우
    @Insert({" INSERT INTO follow (myid, yourid) VALUES (#{useremail}, #{blogemail}) "})
    public int Follow(@Param("useremail") String useremail, @Param("blogemail") String blogemail);

    // 팔로워 목록
    @Select({" SELECT m.email, m.imgkey, m.imgpath, " +
             " (SELECT COUNT(no) FROM follow WHERE myid = #{useremail} AND yourid = m.email) AS follow_yumu FROM follow f " + 
             " LEFT JOIN MEMBER m ON f.myid = m.email " + 
             " WHERE f.yourid = #{blogemail} ORDER BY f.NO DESC "})
    public List<MemberFollowDTO> followerList(@Param("useremail") String useremail, @Param("blogemail") String blogemail);

    // 팔로잉 목록
    @Select({" SELECT m.email, m.imgkey, m.imgpath, " +
             " (SELECT COUNT(no) FROM follow WHERE myid = #{useremail} AND yourid = m.email) AS follow_yumu FROM follow f " + 
             " LEFT JOIN MEMBER m ON f.yourid = m.email " + 
             " WHERE f.myid = #{blogemail} ORDER BY f.NO DESC "})
    public List<MemberFollowDTO> followingList(@Param("useremail") String useremail, @Param("blogemail") String blogemail);
}
