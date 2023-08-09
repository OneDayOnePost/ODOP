package com.example.controller.MH;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.entity.Cate;
import com.example.entity.Post;
import com.example.entity.PostTagProjection;
import com.example.service.MH.PostInsertService;
import com.example.service.MH.PostSelectService;

import lombok.RequiredArgsConstructor;

@RequestMapping(value = "/post")
@RequiredArgsConstructor
@Controller
public class MhPostController {

    final private PostInsertService pService;
    final private PostSelectService postSelectService;
    
    @GetMapping(value = "/write.do")
    public String writeGET(Model model) {

        List<Cate> catelist = pService.selectCateList();

        model.addAttribute("catelist", catelist);

        return "/MH/write";
    }

    @GetMapping(value = "/select.do")
    public String selectGET(Model model) {

        BigInteger postno = new BigInteger("44");

        Post post = postSelectService.selectPostOne(postno);

        model.addAttribute("post", post);

        return "/MH/selectone";
    }

    @GetMapping(value = "/update.do")
    public String updateGET(Model model) {

        BigInteger postno = new BigInteger("40");

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
