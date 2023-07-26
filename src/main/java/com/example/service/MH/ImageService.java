package com.example.service.MH;

import org.springframework.stereotype.Service;

import com.example.entity.Image;

@Service
public interface ImageService {
    
    // 1. 게시글 이미지 등록
    public int insertPostImageOne(Image obj);

}
