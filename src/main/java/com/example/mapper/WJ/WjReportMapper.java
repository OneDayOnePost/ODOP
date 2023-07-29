package com.example.mapper.WJ;

import java.math.BigInteger;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.dto.ReportOneDTO;
import com.example.dto.PostDTO;
import com.example.dto.ReportListDTO;

@Mapper
public interface WjReportMapper {
    // 게시글
    // 1. 전체 신고 목록
    @Select({" SELECT pr.postno AS no, p.writer AS email, COUNT(*) AS reportcount FROM POST_REPORT pr LEFT JOIN post p ON pr.POSTNO = p.NO GROUP BY pr.POSTNO ORDER BY no DESC"})
    public List<ReportListDTO> selectPostListAll();

    // 2. 승인 대기
    // 숫자 2 나중에 변경 해야함 10으로!!!!!
    @Select({" SELECT * FROM (SELECT pr.postno AS no, p.writer AS email, COUNT(*) AS reportcount FROM POST_REPORT pr LEFT JOIN post p ON pr.POSTNO = p.NO WHERE p.state = 0 GROUP BY pr.POSTNO) WHERE reportcount >= 2 ORDER BY reportcount DESC, no DESC "})
    public List<ReportListDTO> selectPostListWait();

    // 3. 삭제 완료
    @Select({" SELECT pr.postno AS no, p.writer AS email, COUNT(*) AS reportcount FROM POST_REPORT pr LEFT JOIN post p ON pr.POSTNO = p.NO WHERE p.state = 1 GROUP BY pr.POSTNO ORDER BY no DESC "})
    public List<ReportListDTO> selectPostListDelete();

    // --------------------------------------------------------------------

    // 게시글 - 상세 조회
    // 게시글 1개 조회
    @Select({" SELECT no, writer, title, content, regdate, state FROM post WHERE no=#{no} "})
    public PostDTO selectPostOne(@Param("no") BigInteger no);

    // 게시글 신고 1개 상세 조회
    @Select({" SELECT reason, COUNT(postno) AS reportcount FROM " +
             " (SELECT pr2.NO, pr2.reason, prp.postno, prp.reasonno FROM POST_REASON pr2 " +
             " LEFT JOIN (SELECT pr.postno, pr.reasonno FROM POST_REPORT pr, post p WHERE pr.POSTNO = p.NO) prp " +
             " ON pr2.NO = prp.reasonno AND prp.postno = #{postno}) " +
             " GROUP BY reason ORDER BY NO ASC "})
    public List<ReportOneDTO> selectPostReportOne(@Param("postno") BigInteger postno);

    // 게시글 신고 삭제 승인
    @Update({" UPDATE post SET state = 1 WHERE no = #{postno} "})
    public int postDeleteOk(@Param("postno") BigInteger postno);

    // 게시글 신고 삭제 거절
    @Delete({" DELETE FROM post_report WHERE postno = #{postno} "})
    public int postDeleteNo(@Param("postno") BigInteger postno);
}
