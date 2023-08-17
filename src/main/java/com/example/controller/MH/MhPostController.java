package com.example.controller.MH;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.entity.Cate;
import com.example.entity.Post;
import com.example.entity.PostTagProjection;
import com.example.service.MH.PostInsertService;
import com.example.service.MH.PostSelectService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(value = "/blog/{email}")
@RequiredArgsConstructor
@Controller
public class MhPostController {

    final private String logger = "MhPostController => {}";

    final private PostInsertService pService;
    final private PostSelectService postSelectService;
    
    @GetMapping(value = "/write.do")
    public String writeGET(Model model) {

        List<Cate> catelist = pService.selectCateList();

        model.addAttribute("catelist", catelist);

        return "/MH/write";
    }

    @GetMapping(value = "/select.do")
    public String selectGET(@RequestParam(name = "postno", required = false, defaultValue = "0") BigInteger postno, Model model) {

        // BigInteger postno = new BigInteger("44");

        /* postno가 없을 경우, myblog 홈으로 이동 */
        if(postno.compareTo(new BigInteger("0")) == 0) {
            return "redirect:home.do";
        }
        
        log.info(logger, postno);

        Post post = postSelectService.selectPostOne(postno);

        model.addAttribute("post", post);

        return "/MH/selectone";
    }

    @GetMapping(value = "/update.do")
    public String updateGET(@RequestParam(name = "postno", required = false, defaultValue = "0") BigInteger postno, Model model) {

        // BigInteger postno = new BigInteger("40");

        /* postno가 없을 경우, myblog 홈으로 이동 */
        if(postno.compareTo(new BigInteger("0")) == 0) {
            return "redirect:home.do";
        }

        log.info(logger, postno);

        List<Cate> catelist = pService.selectCateList();
        Post post = postSelectService.selectPostOne(postno);
        List<PostTagProjection> postTagList = postSelectService.selectPostTagList(postno);

        List<String> tagList = new ArrayList<>();

        for(PostTagProjection tagProjection : postTagList) {
            tagList.add(tagProjection.getTag());
        }

        // log.info(format, tagList.size());

        model.addAttribute("catelist", catelist);
        model.addAttribute("post", post);
        model.addAttribute("tagList", tagList);

        return "/MH/update";
    }

}
