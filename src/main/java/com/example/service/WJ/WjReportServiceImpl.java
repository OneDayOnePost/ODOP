package com.example.service.WJ;

import java.math.BigInteger;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.example.dto.MemberDTO;
import com.example.dto.PostDTO;
import com.example.dto.ReplyDTO;
import com.example.dto.ReportListDTO;
import com.example.dto.ReportOneDTO;
import com.example.entity.Member;
import com.example.mapper.WJ.WjReportMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WjReportServiceImpl implements WjReportService {
    final WjReportMapper rMapper;
    
    // 게시글
    // 1. 전체 신고 목록
    @Override
    public List<ReportListDTO> selectPostListAll() {
        try {
            return rMapper.selectPostListAll();
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // 2. 승인 대기
    @Override
    public List<ReportListDTO> selectPostListWait() {
        try {
            return rMapper.selectPostListWait();
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
 
    // 3. 삭제 완료
    @Override
    public List<ReportListDTO> selectPostListDelete() {
        try {
            return rMapper.selectPostListDelete();
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // ------------------------------------------------------------------------

    // 게시글 - 상세 조회
    // 게시글 1개 조회
    @Override
    public PostDTO selectPostOne(BigInteger no) {
        try {
            return rMapper.selectPostOne(no);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // 게시글 신고 1개 상세 조회
    @Override
    public List<ReportOneDTO> selectPostReportOne(BigInteger postno) {
        try {
            return rMapper.selectPostReportOne(postno);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // 게시글 신고 삭제 승인
    @Override
    public int postDelete(BigInteger postno) {
        try {
            return rMapper.postDelete(postno);
        }
        catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    // 게시글 신고 삭제 거절
    @Override
    public int postReportDelete(BigInteger postno) {
        try {
            return rMapper.postReportDelete(postno);
        }
        catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    // 게시글 삭제 취소
    @Override
    public int postDeleteCancel(BigInteger postno) {
        try {
            return rMapper.postDeleteCancel(postno);
        }
        catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    // --------------------------------------------------------------------

    // 댓글
    // 1. 전체 신고 목록
    @Override
    public List<ReportListDTO> selectReplyListAll() {
        try {
            return rMapper.selectReplyListAll();
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    // 2. 승인 대기
    @Override
    public List<ReportListDTO> selectReplyListWait() {
        try {
            return rMapper.selectReplyListWait();
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    // 3. 삭제 완료
    @Override
    public List<ReportListDTO> selectReplyListDelete() {
        try {
            return rMapper.selectReplyListDelete();
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // ------------------------------------------------------------------------

    // 댓글 - 상세 조회
    // 댓글 1개 조회
    @Override
    public ReplyDTO selectReplyOne(BigInteger no) {
        try {
            return rMapper.selectReplyOne(no);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // 댓글 신고 1개 상세 조회
    @Override
    public List<ReportOneDTO> selectReplyReportOne(BigInteger replyno) {
        try {
            return rMapper.selectReplyReportOne(replyno);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // 댓글 신고 삭제 승인
    @Override
    public int replyDelete(BigInteger replyno) {
        try {
            return rMapper.replyDelete(replyno);
        }
        catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    // 댓글 신고 삭제 거절
    @Override
    public int replyReportDelete(BigInteger replyno) {
        try {
            return rMapper.replyReportDelete(replyno);
        }
        catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    // 댓글 삭제 취소
    @Override
    public int replyDeleteCancel(BigInteger replyno) {
        try {
            return rMapper.replyDeleteCancel(replyno);
        }
        catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    // --------------------------------------------------------------------
    
    // 회원
    // 1. 전체 회원 목록
    @Override
    public List<MemberDTO> selectMemberListAll() {
        try {
            return rMapper.selectMemberListAll();
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // 2. 일반 회원 목록(G, 0), 블랙리스트 회원 목록 (B, -1), 탈퇴한 회원 목록 (L, 1) 
    @Override
    public List<MemberDTO> selectMemberListGBL(BigInteger quitchk) {
        try {
            return rMapper.selectMemberListGBL(quitchk);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // --------------------------------------------------------------------

    // 회원 - 상세 조회
    // 회원 1명 조회
    @Override
    public Member selectMemberOne(String email) {
        try {
            return rMapper.selectMemberOne(email);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // 작성한 게시글 수 (삭제 유무 관련없이 모든 게시글 수)
    @Override
    public int selectPostCount(String email) {
        try {
            return rMapper.selectPostCount(email);
        }
        catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    // 신고되어 삭제된 게시글 수 
    @Override
    public int selectPostReportDeleteCount(String email) {
        try {
            return rMapper.selectPostReportDeleteCount(email);
        }
        catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    // 작성한 댓글 수 (삭제 유무 관련없이 모든 댓글 수)
    @Override
    public int selectReplyCount(String email) {
        try {
            return rMapper.selectReplyCount(email);
        }
        catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    // 신고되어 삭제된 댓글 수
    @Override
    public int selectReplyReportDeleteCount(String email) {
        try {
            return rMapper.selectReplyReportDeleteCount(email);
        }
        catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
