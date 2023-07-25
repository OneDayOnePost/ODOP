package com.example.mapper.WJ;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.dto.ReportListDTO;

@Mapper
public interface WjReportMapper {
    // 게시글
    // 1. 전체 신고 목록
    @Select({" SELECT pr.postno AS no, p.writer AS email, COUNT(*) AS reportcount FROM POST_REPORT pr LEFT JOIN post p ON pr.POSTNO = p.NO GROUP BY pr.POSTNO ORDER BY no DESC"})
    public List<ReportListDTO> selectPostListAll();

    // 2. 승인 대기
    // 숫자 2 나중에 변경 해야함 10으로!!!!!
    @Select({" SELECT * FROM (SELECT pr.postno AS no, p.writer AS email, COUNT(*) AS reportcount FROM POST_REPORT pr LEFT JOIN post p ON pr.POSTNO = p.NO GROUP BY pr.POSTNO) WHERE reportcount >= 2 ORDER BY reportcount DESC, no DESC "})
    public List<ReportListDTO> selectPostListWait();

    // 3. 삭제 완료
}
