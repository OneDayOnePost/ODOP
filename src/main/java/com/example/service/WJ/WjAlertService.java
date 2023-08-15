package com.example.service.WJ;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.example.entity.Alert;

@Service
public interface WjAlertService {
    // 팔로우 알림
    public int followInsert(@Param("email") String email, @Param("content") String content, @Param("url") String url);

    // 알림 조회
    public List<Alert> selectAlertList(@Param("email") String email);
}
