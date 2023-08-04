package com.example.controller.GR;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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
public class GrMemberController {

    // test용
    final GrMyblogMapper gMapper;
    final GrPostRepository postRepository;
    final private GrMemberRepository gRepository;

    @GetMapping(value = "/myblog.do")
    public String myblogGET(Model model,  @AuthenticationPrincipal User user,
        @RequestParam(name = "categoryId", required = false) Long categoryId) { // @AuthenticationPrincipal User user
            try {
                
                // log.info("categoryId received: {}", categoryId);
                log.info("user 정보 => {}", user.toString());
                log.info("user user => {}", user.getAuthorities());

                if (categoryId != null) {
                    log.info("categoryId: {}", categoryId); // categoryId 값 로그로 출력
                    log.info("cate val => {}", BigInteger.valueOf(categoryId));
                    List<Post> list = postRepository.findByWriterAndCateNo(user.getUsername(), BigInteger.valueOf(categoryId));
                    model.addAttribute("list", list);
                    log.info("rkfka => {}", list.toString());

                    // 포스트 갯수 세기
                    int postallcount = list.size();
                    // 포스트 갯수가 한자리일 경우 앞에 0붙이기
                    String formattedpostcount = String.valueOf(postallcount);
                    if (postallcount < 10) {
                        formattedpostcount = "0" + formattedpostcount;
                    }

                    model.addAttribute("formattedpostcount", formattedpostcount);


                } else {
                    // 카테고리를 선택하지 않았을 때의 기본 동작
                    // 기본적으로 전체 포스트 목록을 보여주는 등의 처리를 추가할 수 있음
                    List<Post> list = postRepository.findByWriter(user.getUsername());
                    model.addAttribute("list", list);
                    log.info("skdhkfk => {}", list.toString());

                    // 포스트 갯수 세기
                    int postallcount = list.size();
                    // 포스트 갯수가 한자리일 경우 앞에 0붙이기
                    String formattedpostcount = String.valueOf(postallcount);
                    if (postallcount < 10) {
                        formattedpostcount = "0" + formattedpostcount;
                    }

                    model.addAttribute("formattedpostcount", formattedpostcount);
                }



                    Member member = gRepository.findById(user.getUsername()).orElse(null);
                    int following = gMapper.countfollowing(user.getUsername());
                    int follower = gMapper.countfollower(user.getUsername());

                    log.info("user1 => {}", user.toString());

                    // 카테고리별 게시글 갯수
                    List<Map<String, Integer>> pclist = gMapper.selectpostcatecount(user.getUsername());
                    log.info("listlist=>{}", pclist.toString());

                    model.addAttribute("user", user);
                    model.addAttribute("member", member);
                    model.addAttribute("following", following);
                    model.addAttribute("follower", follower);
                    model.addAttribute("pclist", pclist);
                    return "/GR/myblog";
                } catch (Exception e) {
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
    public String mypageGET(Model model, @AuthenticationPrincipal User user){
        try{
            Member member = gRepository.findById(user.getUsername()).orElse(null);

            model.addAttribute("member", member);
            model.addAttribute("user", user);
            return "/GR/mypage";
        }
        catch(Exception e){
            e.printStackTrace();
            return "/home";
        }
    }

    @PostMapping(value = "/mypage_blog.do")
    public String mypagePOST(@ModelAttribute Member member, @AuthenticationPrincipal User user){

        // 블로그 내용 변경
        try {

            Member m = gRepository.findById(user.getUsername()).orElse(null);

            log.info("rkrkrk => {}", member.toString());
            log.info("가람 => {}", m.toString());

            m.setBlogname(member.getBlogname());
            m.setIntroduce(member.getIntroduce());

            Member ret = gRepository.save(m);

            return "redirect:/mypage.do";

        } catch (Exception e) {
            e.printStackTrace();
            return "/home";
        }
       

    }
    
}