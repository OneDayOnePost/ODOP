package com.example.service.WJ;

import java.math.BigInteger;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.entity.Alert;
import com.example.mapper.WJ.WjAlertMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WjAlertServiceImpl implements WjAlertService {
    final WjAlertMapper aMapper;
    
    // 팔로우 알림
    @Override
    public int followInsert(String email, String content, String url) {
        try {
            return aMapper.followInsert(email, content, url);
        }
        catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    // 알림 조회
    @Override
    public List<Alert> selectAlertList(String email) {
        try {
            return aMapper.selectAlertList(email);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // 알림 상태 업데이트 (chk=0 -> chk=1)
    @Override
    public int updateAlertChk(BigInteger no) {
        try {
            return aMapper.updateAlertChk(no);
        }
        catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    // 읽지 않은(chk=0) 알림 개수
    @Override
    public BigInteger selectAlertCount(String email) {
        try {
            return aMapper.selectAlertCount(email);
        }
        catch (Exception e) {
            e.printStackTrace();
            return BigInteger.valueOf(-1);
        }
    }
}
