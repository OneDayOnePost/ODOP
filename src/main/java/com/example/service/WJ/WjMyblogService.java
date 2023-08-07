package com.example.service.WJ;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.example.entity.Follow;

@Service
public interface WjMyblogService {
    // 팔로우 유무 확인
    public Follow selectFollow(@Param("useremail") String useremail, @Param("blogemail") String blogemail);

    // 언팔로우
    public int UnFollow(@Param("useremail") String useremail, @Param("blogemail") String blogemail);

    // 팔로우
    public int Follow(@Param("useremail") String useremail, @Param("blogemail") String blogemail);
}
