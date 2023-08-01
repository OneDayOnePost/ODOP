package com.example.service.WJ;

import java.math.BigInteger;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Service;

import com.example.dto.MemberDTO;
import com.example.dto.PostDTO;
import com.example.dto.ReplyDTO;
import com.example.dto.ReportListDTO;
import com.example.dto.ReportOneDTO;
import com.example.entity.Member;

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

    // --------------------------------------------------------------------
    
    // 회원
    // 1. 전체 회원 목록
    public List<MemberDTO> selectMemberListAll();

    // 2. 일반 회원 목록(G, 0), 블랙리스트 회원 목록 (B, -1), 탈퇴한 회원 목록 (L, 1) 
    public List<MemberDTO> selectMemberListGBL(@Param("quitchk") BigInteger quitchk);

    // --------------------------------------------------------------------

    // 회원 - 상세 조회
    // 회원 1명 조회
    public Member selectMemberOne(@Param("email") String email);

    // 작성한 게시글 수 (삭제 유무 관련없이 모든 게시글 수)
    public int selectPostCount(@Param("email") String email);

    // 신고되어 삭제된 게시글 수
    public int selectPostReportDeleteCount(@Param("email") String email);

    // 작성한 댓글 수 (삭제 유무 관련없이 모든 댓글 수)
    public int selectReplyCount(@Param("email") String email);

    // 신고되어 삭제된 댓글 수
    public int selectReplyReportDeleteCount(@Param("email") String email);
}
