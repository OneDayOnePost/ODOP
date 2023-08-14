package com.example.service.WJ;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.entity.Cate;

@Service
public interface WjCateService {
    // 카테고리 불러오기
    public List<Cate> selectCateList();
}
