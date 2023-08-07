package com.example.service.WJ;

import org.springframework.stereotype.Service;

import com.example.entity.Follow;
import com.example.mapper.WJ.WjMyblogMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WjMyblogServiceImpl implements WjMyblogService {
    final WjMyblogMapper mbMapper;
    
    // 팔로우 유무 확인
    @Override
    public Follow selectFollow(String useremail, String blogemail) {
        try {
            return mbMapper.selectFollow(useremail, blogemail);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // 언팔로우
    @Override
    public int UnFollow(String useremail, String blogemail) {
        try {
            return mbMapper.UnFollow(useremail, blogemail);
        }
        catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    // 팔로우
    @Override
    public int Follow(String useremail, String blogemail) {
        try {
            return mbMapper.Follow(useremail, blogemail);
        }
        catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
