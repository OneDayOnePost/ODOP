package com.example.service.GR;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.example.dto.PostAllViewDTO;

@Service
public interface GrHomeService {
    // 게시글 목록 불러오기 
    // 최신순
    // public List<PostAllViewDTO> selectPostAllByRegdate();

    // // 좋아요순
    // public List<PostAllViewDTO> selectPostAllByDope();

    // // 팔로워의 글 목록
    // public List<PostAllViewDTO> selectPostAllByFollow( @Param("email") String email);

    // // 제목 키워드 검색
    // public List<PostAllViewDTO> selecttitlekeyword(@Param("keyword") String keyword);
}
