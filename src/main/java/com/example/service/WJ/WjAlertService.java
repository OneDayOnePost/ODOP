package com.example.service.WJ;

import java.math.BigInteger;
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

    // 알림 상태 업데이트 (chk=0 -> chk=1)
    public int updateAlertChk(@Param("no") BigInteger no);

    // 읽지 않은(chk=0) 알림 개수
    public BigInteger selectAlertCount(@Param("email") String email);
}
