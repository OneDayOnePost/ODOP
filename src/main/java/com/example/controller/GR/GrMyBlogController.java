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
import com.example.entity.Post;
import com.example.mapper.GR.GrMyblogMapper;
import com.example.repository.GR.GrPostRepository;

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

    // 이미지 전송용
    @Autowired ResourceLoader resourceLoader; // resources 폴더의 파일을 읽기 위한 객체 생성
    @Value("${default.image}") String DEFAULTIMAGE;

    @GetMapping(value = "/myblog.do")
    public String myblogGET(Model model) { // @AuthenticationPrincipal User user
        try {
            MemberDTO user = gMapper.selectMemberOne("test1@gmail.com");
            int following = gMapper.countfollowing("test1@gmail.com");
            int follower = gMapper.countfollower("test1@gmail.com");

            log.info("user => {}", user.toString());

            // 카테고리별 게시글 갯수
            List<Map<String, Integer>> pclist = gMapper.selectpostcatecount("test1@gmail.com");

            log.info("listlist=>{}", pclist.toString());

          

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
    // 프로필 이미지 url 생성용 => 닉네임을 보내면 프로필 이미지 반환
    // 127.0.0.1:5059/odop/grimage.do?nickname=?
     @GetMapping(value = "/seimage")
    public ResponseEntity<byte[]> image ( @RequestParam(name = "itemno", defaultValue = "0" ) BigDecimal itemno) throws IOException {
        // 메뉴 번호를 입력해서 메뉴 하나 가져오기 (메뉴에 이미지 정보가 있으니까)
        ItemImage obj = piService.selectItemImageOne(itemno);

        HttpHeaders headers = new HttpHeaders(); // import org.springframework.http.HttpHeaders;
        if(obj != null){ // 이미지가 존재하는지 확인
            if(obj.getFilesize().longValue() > 0){
                headers.setContentType( MediaType.parseMediaType(obj.getFiletype()) );
                return new ResponseEntity<>( obj.getFiledata(), headers, HttpStatus.OK );
                //  == 1) ResponseEntity<byte[]> response = new ResponseEntity<>( obj.getFiledata(), headers, HttpStatus.OK );
                // 2) return response;
            }
        }
        // 이미지가 없을 경우
        InputStream is = resourceLoader.getResource(DEFAULTIMAGE).getInputStream(); // exception 발생 => throws IOException 처리
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<>( is.readAllBytes(), headers, HttpStatus.OK );
    }
}
