package com.example.service.GR;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.dto.PostAllViewDTO;
import com.example.mapper.GR.GrHomeMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GrHomeServiceImpl implements GrHomeService{
    final GrHomeMapper hMapper;

    // 홈게시글 불러오기
    // 최신순
    @Override
    public List<PostAllViewDTO> selectPostAllByRegdate() {
        try {
            return hMapper.selectPostAllByRegdate();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }    
    }

    @Override
    public List<PostAllViewDTO> selectPostAllByDope() {
        try {
            return hMapper.selectPostAllByDope();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } 
    }

    @Override
    public List<PostAllViewDTO> selectPostAllByFollow(String email) {
        try {
            return hMapper.selectPostAllByFollow( email);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } 
    }

    @Override
    public List<PostAllViewDTO> selecttitlekeyword(String keyword) {
        try {
            return hMapper.selecttitlekeyword(keyword);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
