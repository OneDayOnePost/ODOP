package com.example.controller.GR;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.catalina.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.dto.CateDTO;
import com.example.dto.MemberDTO;
import com.example.dto.PostDTO;
import com.example.mapper.GR.GrMyblogMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping
@RequiredArgsConstructor
public class GrMyBlogController {

    // test용
    final GrMyblogMapper gMapper;

    @GetMapping(value = "/myblog.do")
    public String myblogGET(Model model) { // @AuthenticationPrincipal User user
        try {
            MemberDTO user = gMapper.selectMemberOne("test1@naver.com");
            int following = gMapper.countfollowing("test1@naver.com");
            int follower = gMapper.countfollower("test1@naver.com");

            log.info("user => {}", user.toString());

            // 카테고리별 게시글 갯수
            List<Map<String, Integer>> pclist = gMapper.selectpostcatecount("test1@naver.com");

            log.info("listlist=>{}", pclist.toString());

            // 포스트 총 갯수 세기
            int postallcount = gMapper.countpostall("test1@naver.com");
            // 포스트 갯수가 한자리일 경우 앞에 0붙이기
            String formattedpostcount = String.valueOf(postallcount);
            if (postallcount < 10) {
                formattedpostcount = "0" + formattedpostcount;
            }

            // 포스트 목록 불러오기
            // List<PostDTO> plist = gMapper.selectpostAll("test1@naver.com");
            // log.info("postall => {}", plist.toString());

            // 태그 불러오기
            // for (int i = 0; i < plist.size(); i++) {
            // plist.get(i).getNo()
            // List<Map<BigInteger, String>> tlist =
            // gMapper.selecttag(plist.get(i).getNo());
            // log.info("rkrkrk=>{}", tlist.toString());
            // log.info(plist.get(i).getNo().toString());
            // }

            // 댓글 갯수
            PostDTO post = new PostDTO();
            // int countreply = gMapper.countreply();

            model.addAttribute("user", user);
            model.addAttribute("following", following);
            model.addAttribute("follower", follower);
            model.addAttribute("pclist", pclist);
            model.addAttribute("formattedpostcount", formattedpostcount);
            // model.addAttribute("plist", plist);
            // model.addAttribute("tlist", tlist);

            return "/GR/myblog";
        } catch (

        Exception e) {
            e.printStackTrace();
            return "/home";
        }
    }
}
