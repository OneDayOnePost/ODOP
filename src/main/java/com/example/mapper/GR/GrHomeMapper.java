package com.example.mapper.GR;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.dto.PostAllViewDTO;

@Mapper
public interface GrHomeMapper {

    // 홈 게시글
    // 최신글 조회
    @Select({" SELECT * FROM POSTALLVIEW ORDER BY regdate desc " })
    public List<PostAllViewDTO> selectPostAllByRegdate();

    // 좋아요 많은 순 조회
    @Select({" SELECT * FROM POSTALLVIEW ORDER BY dope_count desc, regdate desc " })
    public List<PostAllViewDTO> selectPostAllByDope();
}
