package com.example.controller.WJ;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.dto.ReportListDTO;
import com.example.dto.ReportOneDTO;
import com.example.service.WJ.WjReportService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping
@RequiredArgsConstructor
public class WjHomeController {
    final WjReportService rService;
    
    @GetMapping(value = "/adminhome.do")
    public String homeGET(Model model,
                          @AuthenticationPrincipal User user,
                          @RequestParam(name = "type", required = false, defaultValue = "post") String type,
                          @RequestParam(name = "menu", required = false, defaultValue = "wait") String menu) {
        try {
            model.addAttribute("user", user);

            if (type.equals("post")) {
                List<ReportListDTO> postreportlist = rService.selectPostListWait();

                if (menu.equals("all")) {
                    postreportlist = rService.selectPostListAll();
                }
                else if (menu.equals("delete")) {
                    postreportlist = rService.selectPostListDelete();
                }

                model.addAttribute("plist", postreportlist);
            }

            return "/WJ/WjAdminHome";
        } 
        catch (Exception e) {
            e.printStackTrace();
            return "redirect:/home.do";
        }
    }

    // --------------------------------------------------------------------------------

    // 신고된 게시글 상세 조회
    @GetMapping(value = "/postreport.do")
    public String postreportGET(Model model,
                                @AuthenticationPrincipal User user,
                                @RequestParam(name = "type", required = true) String type,
                                @RequestParam(name = "menu", required = true) String menu,
                                @RequestParam(name = "no", required = true) BigInteger no) {
        try {
            model.addAttribute("user", user);

            model.addAttribute("type", type);
            model.addAttribute("menu", menu);
            
            // 게시글 정보
            model.addAttribute("pone", rService.selectPostOne(no));
            // 게시글 신고 정보
            model.addAttribute("prone", rService.selectPostReportOne(no));

            // -------------------------------------------------------------------------
            // 게시글 신고 횟수 합계
            BigInteger sum = BigInteger.ZERO;
            List<ReportOneDTO> reportList = rService.selectPostReportOne(no);
            for (ReportOneDTO reportOne : reportList) {
                sum = sum.add(reportOne.getReportcount());
            }

            model.addAttribute("reportcountsum", sum);

            // -------------------------------------------------------------------------
            // 이전글, 다음글
            List<ReportListDTO> postreportlist = new ArrayList<>();
            if (menu.equals("all")) {
                postreportlist = rService.selectPostListAll();
            } else if (menu.equals("wait")) {
                postreportlist = rService.selectPostListWait();
            } else if (menu.equals("delete")) {
                postreportlist = rService.selectPostListDelete();
            }

            int currentIndex = -1;
            for (int i = 0; i < postreportlist.size(); i++) {
                ReportListDTO postreport = postreportlist.get(i);
                if (postreport.getNo().equals(no)) {
                    currentIndex = i;
                    break;
                }
            }

            // 현재 게시물 번호의 인덱스를 찾았으면, 이전글과 다음글을 리스트에서 찾아냄
            BigInteger prev = BigInteger.ZERO; // 이전글 
            BigInteger next = BigInteger.ZERO; // 다음글
            if (currentIndex >= 0) {
                if (currentIndex > 0) {
                    next = postreportlist.get(currentIndex - 1).getNo();
                }
                if (currentIndex < postreportlist.size() - 1) {
                    prev = postreportlist.get(currentIndex + 1).getNo();
                }
            }

            model.addAttribute("prev", prev);
            model.addAttribute("next", next);

            return "/WJ/WjPostReportDetail";
        }
        catch (Exception e) {
            e.printStackTrace();
            return "redirect:/home.do";
        }
    }

}
