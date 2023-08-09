package com.example.restcontroller.WJ;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Follow;
import com.example.service.WJ.WjMyblogService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/api/myblog")
@RequiredArgsConstructor
@Slf4j
public class WjRestMyblogController {
    final WjMyblogService mbService;

    // myblog 페이지
    // (1) 팔로우 취소 버튼 (follow 테이블에서 삭제)
    @DeleteMapping(value = "/followdelete.json")
    public Map<String, Object> followdeleteDELETE(@RequestParam(name = "useremail") String useremail,
                                                  @RequestParam(name = "blogemail") String blogemail) {
        Map<String, Object> retMap = new HashMap<>();

        try {
            int ret = mbService.UnFollow(useremail, blogemail);

            if (ret == 1) {
                retMap.put("status", 200);
            } else {
                retMap.put("status", 0);
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
            retMap.put("status", -1);
            retMap.put("error", e.getMessage());
        }

        return retMap;
    }

    // (2) 팔로우 버튼 (follow 테이블에 추가)
    @PostMapping(value = "/followinsert.json")
    public Map<String, Object> followinsertPOST(@RequestBody Follow follow) {
        Map<String, Object> retMap = new HashMap<>();
        
        try {
            int ret = mbService.Follow(follow.getFromMember().getEmail(), follow.getToMember().getEmail());

            if (ret == 1) {
                retMap.put("status", 200);
            } else {
                retMap.put("status", 0);
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
            retMap.put("status", -1);
            retMap.put("error", e.getMessage());
        }

        return retMap;
    }

    // 팔로워 목록
    @GetMapping(value = "/followerlist.json")
    public Map<String, Object> followerlistGET(@RequestParam(name = "useremail") String useremail,
                                               @RequestParam(name = "blogemail") String blogemail){
        Map<String, Object> retMap = new HashMap<>();

        try {
            retMap.put("status", 200);
            retMap.put("followerlist", mbService.followerList(useremail, blogemail));
        } 
        catch (Exception e) {
            e.printStackTrace();
            retMap.put("status", -1);
            retMap.put("error", e.getMessage());
        }

        return retMap;
    }

    // 팔로잉 목록
    @GetMapping(value = "/followinglist.json")
    public Map<String, Object> followinglistGET(@RequestParam(name = "useremail") String useremail,
                                                @RequestParam(name = "blogemail") String blogemail){
        Map<String, Object> retMap = new HashMap<>();

        try {
            retMap.put("status", 200);
            retMap.put("followinglist", mbService.followingList(useremail, blogemail));
        } 
        catch (Exception e) {
            e.printStackTrace();
            retMap.put("status", -1);
            retMap.put("error", e.getMessage());
        }

        return retMap;
    }
}
