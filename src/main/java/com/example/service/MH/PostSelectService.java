package com.example.service.MH;

import java.math.BigInteger;

import org.springframework.stereotype.Service;

import com.example.entity.Post;

@Service
public interface PostSelectService {

    // 1. 글 하나 조회
    public Post selectPostOne(BigInteger no);
    
}
