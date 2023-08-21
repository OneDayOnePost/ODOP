package com.example.restcontroller.MH;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Post;
import com.example.entity.Reply;
import com.example.service.MH.PostSelectService;
import com.example.service.MH.ReplyService;
import com.example.service.WJ.WjAlertService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/reply")
public class RestReplyController {

    final String format = "RestReplyController => {}";

    final private ReplyService replyService;

    final private PostSelectService postselectService;
    final private WjAlertService WjaService;

    @PostMapping(value = "/write.json")
    public Map<String, Object> writePOST(@RequestBody Reply obj, @AuthenticationPrincipal User user) {

        Map<String, Object> retMap = new HashMap<>();

        retMap.put("status", -1);

        log.info(format, obj.toString());

        obj.setWriter(user.getUsername());

        int ret = replyService.insertReplyOne(obj);

        if(ret == 1) {

            retMap.put("status", 200);

            // 글 작성자 -> 댓글 알림
            // Post post = postselectService.selectPostOne(obj.getPost().getNo());
            // String email = post.getWriter();
            // if (!email.equals(user.getUsername())) { // 본인 글에 댓글을 달 경우에는 알림 X
            //     String title = (post.getTitle().length() > 7) ? post.getTitle().substring(0, 7) + "..." : post.getTitle();
            //     String content = "[" + title + "]에 댓글이 달렸습니다.";
            //     String url = "/blog/" + email + "/select.do?postno=" + post.getNo();
            //     WjaService.alertInsert(email, content, "댓글", url, obj.getRegdate());
            // }

        }

        return retMap;

    }

    @GetMapping(value = "/select.json")
    public Map<String, Object> selectGET(@RequestParam(name = "postno") BigInteger postno) {

        Map<String, Object> retMap = new HashMap<>();

        // log.info(format, postno);

        List<Reply> list = replyService.selectReplyList(postno);

        // log.info(format, list);

        retMap.put("status", -1);

        if(!list.isEmpty()) {
            retMap.put("status", 200);
            retMap.put("list", list);
        }

        return retMap;

    }

    @PutMapping(value = "/delete.json")
    public Map<String, Object> deletePUT(@RequestBody Reply obj) {

        Map<String, Object> retMap = new HashMap<>();

        retMap.put("status", -1);

        int ret = replyService.deleteReplyOne(obj);

        if(ret == 1) {
            retMap.put("status", 200);
        }

        return retMap;

    }

    @PutMapping(value = "/update.json")
    public Map<String, Object> updatePUT(@RequestBody Reply obj) {

        Map<String, Object> retMap = new HashMap<>();

        retMap.put("status", -1);

        int ret = replyService.updateReplyOne(obj);

        if(ret == 1) {
            retMap.put("status", 200);
        }

        return retMap;

    }
    
}
