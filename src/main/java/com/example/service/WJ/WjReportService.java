package com.example.service.WJ;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.dto.ReportListDTO;

@Service
public interface WjReportService {
    // 게시글 신고 목록
    public List<ReportListDTO> selectPostList();
}
