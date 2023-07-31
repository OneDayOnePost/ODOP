package com.example.service.WJ;

import java.math.BigInteger;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Service;

import com.example.dto.PostDTO;
import com.example.dto.ReplyDTO;
import com.example.dto.ReportListDTO;
import com.example.dto.ReportOneDTO;

@Service
public interface WjReportService {
    // 게시글
    // 1. 전체 신고 목록
    public List<ReportListDTO> selectPostListAll();

    // 2. 승인 대기
    public List<ReportListDTO> selectPostListWait();

    // 3. 삭제 완료
    public List<ReportListDTO> selectPostListDelete();

    // ------------------------------------------------------------------------

    // 게시글 - 상세 조회
    // 게시글 1개 조회
    public PostDTO selectPostOne(@Param("no") BigInteger no);

    // 게시글 신고 1개 상세 조회
    public List<ReportOneDTO> selectPostReportOne(@Param("postno") BigInteger postno);

    // 게시글 신고 삭제 승인
    public int postDelete(@Param("postno") BigInteger postno);

    // 게시글 신고 삭제 거절
    public int postReportDelete(@Param("postno") BigInteger postno);

    // 게시글 삭제 취소
    public int postDeleteCancel(@Param("postno") BigInteger postno);

    // --------------------------------------------------------------------

    // 댓글
    // 1. 전체 신고 목록
    public List<ReportListDTO> selectReplyListAll();

    // 2. 승인 대기
    public List<ReportListDTO> selectReplyListWait();

    // 3. 삭제 완료
    public List<ReportListDTO> selectReplyListDelete();

    // --------------------------------------------------------------------

    // 댓글 - 상세 조회
    // 댓글 1개 조회
    public ReplyDTO selectReplyOne(@Param("no") BigInteger no);

    // 댓글 신고 1개 상세 조회
    public List<ReportOneDTO> selectReplyReportOne(@Param("replyno") BigInteger replyno);

    // 댓글 신고 삭제 승인
    public int replyDelete(@Param("replyno") BigInteger replyno);

    // 댓글 신고 삭제 거절
    public int replyReportDelete(@Param("replyno") BigInteger replyno);

    // 댓글 삭제 취소
    public int replyDeleteCancel(@Param("replyno") BigInteger replyno);
}
