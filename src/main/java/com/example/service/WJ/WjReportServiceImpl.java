package com.example.service.WJ;

import java.math.BigInteger;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.example.dto.PostDTO;
import com.example.dto.ReportListDTO;
import com.example.dto.ReportOneDTO;
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
    public int postDeleteOk(BigInteger postno) {
        try {
            return rMapper.postDeleteOk(postno);
        }
        catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    // 게시글 신고 삭제 거절
    @Override
    public int postDeleteNo(BigInteger postno) {
        try {
            return rMapper.postDeleteNo(postno);
        }
        catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
