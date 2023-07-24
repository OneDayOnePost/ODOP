package com.example.mapper.WJ;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.dto.ReportListDTO;

@Mapper
public interface WjReportMapper {
    // 게시글 신고 목록
    // 게시글 신고 횟수가 10번 넘은 글들만 조회하려면 아래 코드로 변경!! 숫자도 2에서 10으로 변경!!
    // SELECT * FROM (SELECT pr.postno AS postno, p.writer AS writer, COUNT(*) AS reportcount FROM POST_REPORT pr LEFT JOIN post p ON pr.POSTNO = p.NO GROUP BY pr.POSTNO) WHERE reportcount >= 2 ORDER BY postno DESC
    @Select({" SELECT * FROM (SELECT pr.postno AS no, p.writer AS email, COUNT(*) AS reportcount FROM POST_REPORT pr LEFT JOIN post p ON pr.POSTNO = p.NO GROUP BY pr.POSTNO) ORDER BY reportcount DESC, no DESC "})
    // @Select({"SELECT pr.postno AS no, p.writer AS email, COUNT(*) AS reportcount FROM POST_REPORT pr LEFT JOIN post p ON pr.POSTNO = p.NO GROUP BY pr.POSTNO ORDER BY postno DESC"})
    public List<ReportListDTO> selectPostList();
}
