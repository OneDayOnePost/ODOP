package com.example.service.MH;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.entity.Cate;
import com.example.entity.Post;

@Service
public interface PostInsertService {
    
    // 1. 카테고리 정보 호출
    public List<Cate> selectCateList();

    // 2. 글 등록
    public int insertPostOne(Post obj);

}
