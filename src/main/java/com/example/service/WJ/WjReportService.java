package com.example.service.WJ;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.dto.ReportListDTO;

@Service
public interface WjReportService {
    // 게시글
    // 1. 전체 신고 목록
    public List<ReportListDTO> selectPostListAll();

    // 2. 승인 대기
    public List<ReportListDTO> selectPostListWait();

    // 3. 삭제 완료
}
