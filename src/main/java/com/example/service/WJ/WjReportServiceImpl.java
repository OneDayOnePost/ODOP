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
}
