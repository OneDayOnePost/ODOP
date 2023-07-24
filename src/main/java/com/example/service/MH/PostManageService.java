package com.example.service.MH;

import org.springframework.stereotype.Service;

import com.example.entity.Post;

@Service
public interface PostManageService {
    
    // 1. 글 수정
    public int updatePostOne(Post obj);

}
