package com.example.restcontroller.WJ;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.MemberFollowDTO;
import com.example.entity.Follow;
import com.example.service.WJ.WjAlertService;
import com.example.service.WJ.WjMemberService;
import com.example.service.WJ.WjMyblogService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/api/myblog")
@RequiredArgsConstructor
@Slf4j
public class WjRestMyblogController {
    final WjMyblogService mbService;
    final WjAlertService aService;
    final WjMemberService mService;

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
            String fromMember = follow.getFromMember().getEmail();
            String toMember = follow.getToMember().getEmail();

            int ret = mbService.Follow(fromMember, toMember);
            
            if (ret == 1) {
                retMap.put("status", 200);
                
                // 팔로우 알림
                String content = "<b>" + mService.findByEmail(fromMember).getNickname() + "</b>님이 회원님을 팔로우하셨습니다.";
                String url = "/blog/" + fromMember + "/home.do";
                Date regdate = new Date();
                aService.alertInsert(toMember, content, "팔로우", url, regdate);
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
            List<MemberFollowDTO> followerlist = mbService.followerList(useremail, blogemail);

            for (MemberFollowDTO follower : followerlist) {
                follower.setNickname(mService.findByEmail(follower.getEmail()).getNickname());
            }

            retMap.put("status", 200);
            retMap.put("followerlist", followerlist);
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
            List<MemberFollowDTO> followinglist = mbService.followingList(useremail, blogemail);

            for (MemberFollowDTO following : followinglist) {
                following.setNickname(mService.findByEmail(following.getEmail()).getNickname());
            }

            retMap.put("status", 200);
            retMap.put("followinglist", followinglist);
        } 
        catch (Exception e) {
            e.printStackTrace();
            retMap.put("status", -1);
            retMap.put("error", e.getMessage());
        }

        return retMap;
    }
}
