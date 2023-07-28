package com.example.restcontroller.WJ;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.WJ.WjReportService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
@Slf4j
public class WjRestHomeController {
    final WjReportService rService;
    
    // @GetMapping(value = "/selectedpostmenu.json")
    // public Map<String, Object> postmenuGET(@RequestParam(name = "menu") String menu) {
    //     Map<String, Object> retMap = new HashMap<>();

    //     try {
    //         if (menu.equals("all")) {
    //             // 전체 신고 목록
    //             retMap.put("status", 200);
    //             retMap.put("ret", rService.selectPostListAll());
    //         }
    //         else if (menu.equals("wait")) {
    //             // 승인 대기
    //             retMap.put("status", 200);
    //             retMap.put("ret", rService.selectPostListWait());
    //         }
    //         else if (menu.equals("delete")) {
    //             // 삭제 완료
    //             retMap.put("status", 200);
    //             retMap.put("ret", rService.selectPostListDelete());
    //         }
    //     }
    //     catch (Exception e) {
    //         e.printStackTrace();
    //         retMap.put("status", -1);
    //         retMap.put("error", e.getMessage());
    //     }

    //     return retMap;
    // }
}
