package com.example.controller.WJ;

import java.math.BigInteger;
import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.dto.ReportListDTO;
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
                List<ReportListDTO> postreportlist= rService.selectPostListWait();

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
                                @RequestParam(name = "no", required = false, defaultValue = "wait") BigInteger no) {
        try {
            model.addAttribute("user", user);
            model.addAttribute("pone", rService.selectPostOne(no));
            model.addAttribute("prone", rService.selectPostReportOne(no));

            return "/WJ/WjPostReportDetail";
        }
        catch (Exception e) {
            e.printStackTrace();
            return "redirect:/home.do";
        }
    }

}
