package com.example.controller;

import java.math.BigInteger;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.dto.PostAllViewDTO;
import com.example.entity.Cate;
import com.example.service.GR.GrHomeService;
import com.example.service.WJ.WjAlertService;
import com.example.service.WJ.WjCateService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping
@RequiredArgsConstructor
public class HomeController {
    final WjCateService WjcService;
    final WjAlertService WjaService;
    final GrHomeService GhService;

    @GetMapping(value = "/{type}/home.do")
    public String homeGET(@AuthenticationPrincipal User user, Model model, HttpSession httpSession,
                            @PathVariable(value = "type") String type) {

        // if(httpSession.getAttribute("cateList") != null && httpSession.getAttribute("mbtiList") != null) {

        //     List<BigInteger> cateList = (List<BigInteger>) httpSession.getAttribute("cateList");
        //     List<String> mbtiList = (List<String>) httpSession.getAttribute("mbtiList");

        //     log.info("homeGET => {}", cateList.toString());
        //     log.info("homeGET => {}", mbtiList.toString());

        //     model.addAttribute("category", cateList);
        //     model.addAttribute("mbti", mbtiList);
        // }

        if (type.equals("newest")) {
        List<PostAllViewDTO> postalllist = GhService.selectPostAllByRegdate();

        model.addAttribute("plist", postalllist);

        } else if (type.equals("like")) {
        List<PostAllViewDTO> postalllist = GhService.selectPostAllByDope();

        model.addAttribute("plist", postalllist);
        } else if (type.equals(("follow"))) {
        List<PostAllViewDTO> postalllist =
        GhService.selectPostAllByFollow(user.getUsername());

        model.addAttribute("plist", postalllist);
        }

        return "/home";

    }

    @PostMapping(value = "/home.do")
    public String homePOST(
            @RequestParam(name = "category", required = false, defaultValue = "") List<Object> cateList,
            @RequestParam(name = "mbti", required = false, defaultValue = "") List<Object> mbtiList,
            HttpSession httpSession) {

        log.info("homePOST => {}", cateList.toString());
        log.info("homePOST => {}", mbtiList.toString());

        httpSession.setAttribute("cateList", cateList);
        httpSession.setAttribute("mbtiList", mbtiList);

        return "redirect:/home.do";
    }

    @ControllerAdvice
    public class GlobalControllerAdvice {

        // header - 서브 메뉴 (카테고리)
        // categories 데이터가 모든 페이지의 모델에 자동으로 추가

        @ModelAttribute("categories")
        public List<Cate> getCategories() {
            List<Cate> categories = WjcService.selectCateList();

            return categories;
        }

        // header - 유저 정보
        // user 로그인 중인 유저 데이터가 모든 페이지의 모델에 자동 추가

        @ModelAttribute("user")
        public User getUser(@AuthenticationPrincipal User user) {

            // log.info("HomeController => {}", user);
            
            return user;
        
        }

        // header - 알림 개수
        @ModelAttribute("alertcount")
        public BigInteger getAlertCount(@AuthenticationPrincipal User user) {
            BigInteger alertcount = BigInteger.valueOf(0);
            
            if (user != null) {
                alertcount = WjaService.selectAlertCount(user.getUsername());
            }

            return alertcount;
        }
    }
}
