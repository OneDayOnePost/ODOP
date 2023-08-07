package com.example.service.WJ;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface WjCateService {
    // 카테고리 불러오기
    public List<String> selectCateList();
}
