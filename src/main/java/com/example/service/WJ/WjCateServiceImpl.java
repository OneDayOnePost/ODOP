package com.example.service.WJ;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.mapper.WJ.WjCateMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WjCateServiceImpl implements WjCateService {
    final WjCateMapper cMapper;
    
    // 카테고리 불러오기
    @Override
    public List<String> selectCateList() {
        try {
            return cMapper.selectCateList();
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
