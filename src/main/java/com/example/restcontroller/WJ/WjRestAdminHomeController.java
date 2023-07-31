package com.example.restcontroller.WJ;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.PostDTO;
import com.example.service.WJ.WjReportService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
@Slf4j
public class WjRestAdminHomeController {
    final WjReportService rService;

    // 신고된 게시글 상세 페이지
    // (1) 삭제 승인
    @PutMapping(value = "/postdelete.json")
    public Map<String, Object> postdeletePUT(@RequestBody PostDTO post) {
        Map<String, Object> retMap = new HashMap<>();

        try {
            int ret = rService.postDelete(post.getNo());

            if  (ret == 1) {
                retMap.put("status", 200);
            }
            else {
                retMap.put("status", 0);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            retMap.put("status", -1);
            retMap.put("error", e.getMessage());
        }

        return retMap;
    }

    // (2) 삭제 거절 -> post_report 테이블에서 해당 게시글 삭제
    @DeleteMapping(value = "/reportdelete.json")
    public Map<String, Object> reportdeleteDELETE(@RequestParam(name = "no") String no) {
        Map<String, Object> retMap = new HashMap<>();

        try {
            BigInteger postno = new BigInteger(no);
            int ret = rService.reportDelete(postno);

            if  (ret >= 1) {
                retMap.put("status", 200);
            }
            else {
                retMap.put("status", 0);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            retMap.put("status", -1);
            retMap.put("error", e.getMessage());
        }

        return retMap;
    }
}
