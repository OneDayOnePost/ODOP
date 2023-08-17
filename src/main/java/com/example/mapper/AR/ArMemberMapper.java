package com.example.mapper.AR;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.dto.MemberDTO;

@Mapper
public interface ArMemberMapper {

    //이메일 찾기
    @Select({" SELECT email, password FROM MEMBER WHERE email=#{email} "})
    public List<MemberDTO> selectMemberEmail(@Param("email") String email);


    // 비밀번호 찾기 및 변경
    @Update({"UPDATE MEMBER SET password=#{member.password} WHERE email=#{member.email}"})
    public int updateMemberPassword(@Param("member") MemberDTO member);

}
