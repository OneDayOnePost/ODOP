package com.example.controller.GR;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.dto.MemberDTO;
import com.example.entity.Member;
import com.example.entity.Post;
import com.example.mapper.GR.GrMyblogMapper;
import com.example.repository.GR.GrMemberRepository;
import com.example.repository.GR.GrPostRepository;
import com.example.repository.MH.MhMemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j 
@RequestMapping
@RequiredArgsConstructor
public class GrMyBlogController {

    // test용
    final GrMyblogMapper gMapper;
    final GrPostRepository postRepository;
    final private GrMemberRepository gRepository;

    @GetMapping(value = "/myblog.do")
    public String myblogGET(Model model) { // @AuthenticationPrincipal User user
        try {
            Member user = gRepository.findById("test1@gmail.com").orElse(null);
            int following = gMapper.countfollowing("test1@gmail.com");
            int follower = gMapper.countfollower("test1@gmail.com");

            log.info("user => {}", user.toString());

            // 카테고리별 게시글 갯수
            List<Map<String, Integer>> pclist = gMapper.selectpostcatecount("test1@gmail.com");
            // log.info("listlist=>{}", pclist.toString());


            // 포스트 총 갯수 세기
            int postallcount = gMapper.countpostall("test1@gmail.com");
            // 포스트 갯수가 한자리일 경우 앞에 0붙이기
            String formattedpostcount = String.valueOf(postallcount);
            if (postallcount < 10) {
                formattedpostcount = "0" + formattedpostcount;
            }

            // 포스트 목록 불러오기 
            List<Post> list = postRepository.findByWriter("test1@gmail.com");
            log.info("list => {}", list.toString());

            model.addAttribute("user", user);
            model.addAttribute("following", following);
            model.addAttribute("follower", follower);
            model.addAttribute("pclist", pclist);
            model.addAttribute("formattedpostcount", formattedpostcount);
            model.addAttribute("list", list);

            return "/GR/myblog";
        } catch (

        Exception e) {
            e.printStackTrace();
            return "/home";
        }
    }

    // -----------------------------------------------------------------------------------
    // 헤더 테스트용
    @GetMapping(value = "/headertest.do")
    public String headertestGET(Model model){

        return "/GR/grheader";
    }

    // -----------------------------------------------------------------------------------

    //  마이페이지
    @GetMapping(value="/mypage.do")
    public String mypageGET(Model model){
        try{
            Member user = gRepository.findById("test1@gmail.com").orElse(null);

            model.addAttribute("user", user);
            return "/GR/mypage";
        }
        catch(Exception e){
            e.printStackTrace();
            return "/myblog";
        }
    }
    
}
