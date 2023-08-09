package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.dto.PostAllViewDTO;
import com.example.service.GR.GrHomeService;
import com.example.service.WJ.WjCateService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping
@RequiredArgsConstructor
public class HomeController {
    final WjCateService WjcService;
    final GrHomeService GhService;

    @GetMapping(value = "/home.do")
    public String homeGET(@AuthenticationPrincipal User user, Model model,
                            @RequestParam(name = "type", required = false, defaultValue = "newest") String type) {
        
        try {
            model.addAttribute("user", user);

            if(type.equals("newest")){
                List<PostAllViewDTO> postalllist = GhService.selectPostAllByRegdate();

                model.addAttribute("plist", postalllist);

            }
            else if(type.equals("like")){
                List<PostAllViewDTO> postalllist = GhService.selectPostAllByDope();
                
                
                model.addAttribute("plist", postalllist);
            }
            else if (type.equals(("follow"))){
                List<PostAllViewDTO> postalllist = GhService.selectPostAllByFollow( user.getUsername());
            
                model.addAttribute("plist", postalllist);
            }

            return "/home";
            
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/home.do";
        }
        

    }

    // header - 서브 메뉴 (카테고리)
    // categories 데이터가 모든 페이지의 모델에 자동으로 추가
    @ControllerAdvice
    public class GlobalControllerAdvice {
        @ModelAttribute("categories")
        public List<String> getCategories() {
            List<String> categories = WjcService.selectCateList();

            return categories;
        }
    }
}
