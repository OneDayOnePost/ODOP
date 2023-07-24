package com.example.controller.WJ;

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
                          @RequestParam(name = "type", required = false, defaultValue = "post") String type) {
        try {
            model.addAttribute("user", user);

            if (type.equals("post")) {
                List<ReportListDTO> postreportlist= rService.selectPostList();
                model.addAttribute("plist", postreportlist);
            }

            return "/WJ/WjAdminHome";
        } 
        catch (Exception e) {
            e.printStackTrace();
            return "redirect:/home.do";
        }
    }
}
