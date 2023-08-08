package com.example.service.GR;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.dto.PostAllViewDTO;

@Service
public interface GrHomeService {
    // 게시글 목록 불러오기 
    // 최신순
    public List<PostAllViewDTO> selectPostAllByRegdate();

    // 좋아요순
    public List<PostAllViewDTO> selectPostAllByDope();
}
