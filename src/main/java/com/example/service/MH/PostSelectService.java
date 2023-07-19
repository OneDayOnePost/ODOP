package com.example.service.MH;

import java.math.BigInteger;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.entity.Post;
import com.example.entity.PostTag;
import com.example.entity.PostTagProjection;

@Service
public interface PostSelectService {

    // 1. 글 하나 조회
    public Post selectPostOne(BigInteger no);

    // 2. 글 해시태그 목록 조회
    public List<PostTagProjection> selectPostTagList(BigInteger no);
    
}
