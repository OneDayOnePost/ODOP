package com.example.mapper.GR;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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

    // 팔로우 한 사람 글 조회
    @Select({" SELECT f.yourid, p.* FROM follow f, POSTALLVIEW  p " +
               " WHERE f.myid = #{email} AND f.yourid = p.writer ORDER BY regdate desc"})
    public List<PostAllViewDTO> selectPostAllByFollow( @Param("email") String email);

    // 제목 키워드 검색
    @Select({" SELECT * FROM postallview WHERE title LIKE CONCAT('%', #{keyword}, '%') "})
    public List<PostAllViewDTO> selecttitlekeyword(@Param("keyword") String keyword);
}
