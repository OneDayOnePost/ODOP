package com.example.service.WJ;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.dto.ReportListDTO;
import com.example.mapper.WJ.WjReportMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WjReportServiceImpl implements WjReportService {
    final WjReportMapper rMapper;

    // 게시글 신고 목록
    @Override
    public List<ReportListDTO> selectPostList() {
        try {
            return rMapper.selectPostList();
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
