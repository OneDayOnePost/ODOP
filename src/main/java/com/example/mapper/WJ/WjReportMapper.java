package com.example.mapper.WJ;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.dto.ReportListDTO;

@Mapper
public interface WjReportMapper {
    // 게시글 신고 목록
    @Select({"SELECT pr.postno AS no, p.writer AS email, COUNT(*) AS reportcount FROM POST_REPORT pr LEFT JOIN post p ON pr.POSTNO = p.NO GROUP BY pr.POSTNO ORDER BY postno DESC"})
    public List<ReportListDTO> selectPostList();
}
