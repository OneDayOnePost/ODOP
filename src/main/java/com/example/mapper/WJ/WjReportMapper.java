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
import com.example.dto.ReplyDTO;
import com.example.dto.ReportListDTO;

@Mapper
public interface WjReportMapper {
    // 게시글
    // 1. 전체 신고 목록
    @Select({" SELECT pr.postno AS no, p.writer AS email, COUNT(*) AS reportcount FROM POST_REPORT pr LEFT JOIN post p ON pr.POSTNO = p.NO GROUP BY pr.POSTNO ORDER BY no DESC "})
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
    public int postDelete(@Param("postno") BigInteger postno);

    // 게시글 신고 삭제 거절
    @Delete({" DELETE FROM post_report WHERE postno = #{postno} "})
    public int postReportDelete(@Param("postno") BigInteger postno);

    // 게시글 삭제 취소
    @Update({" UPDATE post SET state = 0 WHERE no = #{postno} "})
    public int postDeleteCancel(@Param("postno") BigInteger postno);

    // --------------------------------------------------------------------

    // 댓글
    // 1. 전체 신고 목록
    @Select({" SELECT rr.REPLYNO AS no, r.writer AS email, COUNT(*) AS reportcount FROM REPLY_REPORT rr LEFT JOIN reply r ON rr.REPLYNO = r.NO GROUP BY rr.REPLYNO ORDER BY no DESC "})
    public List<ReportListDTO> selectReplyListAll();

    // 2. 승인 대기
    // 숫자 2 나중에 변경 해야함 10으로!!!!!
    @Select({" SELECT * FROM (SELECT rr.replyno AS no, r.writer AS email, COUNT(*) AS reportcount FROM REPLY_REPORT rr LEFT JOIN REPLY r ON rr.replyno = r.NO WHERE r.state = 0 GROUP BY rr.replyno) WHERE reportcount >= 2 ORDER BY reportcount DESC, no DESC "})
    public List<ReportListDTO> selectReplyListWait();

    // 3. 삭제 완료
    @Select({" SELECT rr.REPLYNO AS no, r.writer AS email, COUNT(*) AS reportcount FROM REPLY_REPORT rr LEFT JOIN REPLY r ON rr.REPLYNO = r.NO WHERE r.state = 1 GROUP BY rr.REPLYNO ORDER BY no DESC "})
    public List<ReportListDTO> selectReplyListDelete();

    // --------------------------------------------------------------------

    // 댓글 - 상세 조회
    // 댓글 1개 조회
    @Select({" SELECT no, writer, content, regdate, state FROM reply WHERE no=#{no} "})
    public ReplyDTO selectReplyOne(@Param("no") BigInteger no);

    // 댓글 신고 1개 상세 조회
    @Select({" SELECT reason, COUNT(replyno) AS reportcount FROM " + 
             " (SELECT rr2.NO, rr2.reason, rrr.replyno, rrr.reasonno FROM REPLY_REASON rr2 " + 
             " LEFT JOIN (SELECT rr.replyno, rr.reasonno FROM REPLY_REPORT rr, REPLY r WHERE rr.replyno = r.NO) rrr " + 
             " ON rr2.NO = rrr.reasonno AND rrr.replyno = #{replyno}) " + 
             " GROUP BY reason ORDER BY NO ASC "})
    public List<ReportOneDTO> selectReplyReportOne(@Param("replyno") BigInteger replyno);

    // 댓글 신고 삭제 승인
    @Update({" UPDATE reply SET state = 1 WHERE no = #{replyno} "})
    public int replyDelete(@Param("replyno") BigInteger replyno);

    // 댓글 신고 삭제 거절
    @Delete({" DELETE FROM reply_report WHERE replyno = #{replyno} "})
    public int replyReportDelete(@Param("replyno") BigInteger replyno);

    // 댓글 삭제 취소
    @Update({" UPDATE reply SET state = 0 WHERE no = #{replyno} "})
    public int replyDeleteCancel(@Param("replyno") BigInteger replyno);
}
