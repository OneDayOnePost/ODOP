package com.example.service.WJ;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.example.dto.MemberFollowDTO;
import com.example.entity.Follow;

@Service
public interface WjMyblogService {
    // 팔로우 유무 확인
    public Follow selectFollow(@Param("useremail") String useremail, @Param("blogemail") String blogemail);

    // 팔로우 취소
    public int UnFollow(@Param("useremail") String useremail, @Param("blogemail") String blogemail);

    // 팔로우
    public int Follow(@Param("useremail") String useremail, @Param("blogemail") String blogemail);

    // 팔로워 목록
    public List<MemberFollowDTO> followerList(@Param("useremail") String useremail, @Param("blogemail") String blogemail);
    
    // 팔로잉 목록
    public List<MemberFollowDTO> followingList(@Param("useremail") String useremail, @Param("blogemail") String blogemail);

}
